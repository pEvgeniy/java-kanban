import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.Managers;
import service.managers.TaskManager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

//        Создаем задачу task1 и добавляем
        Task task1 = new Task("Пробежка", "Выйти на пробежку в 10:00", TaskStatus.NEW);
        taskManager.addTask(task1);

//        Создаем 2 подзадачи с TaskStatus.NEW и добавляем в созданный эпик epic1
        Epic epic1 = new Epic("Сделать ТЗ", "Выполнить все, что нужно", twoNewSubtasks());
        taskManager.addTask(epic1);

//        Создаем 1 подзадачу с TaskStatus.NEW и добавляем в созданный эпик epic2
        Epic epic2 = new Epic("Сделать тесты", "Придумать тесты", oneNewSubtask());
        taskManager.addTask(epic2);
        System.out.println("\n");

//        Выводим каждую задачу по одной
        System.out.println(taskManager.getTask(1));
        System.out.println(taskManager.getTask(3));
        System.out.println("\n");

//        Выводим подзадачи эпиков
        System.out.println(taskManager.getSubtask(2, 2));
        for (int i = 0; i < 8; i++) {
            taskManager.getSubtask(3, 1);
        }
        System.out.println("\n");

//        Выводим историю при 11-ти просмотрах и добавляем 12-ый
        System.out.println("historyManager:");
        System.out.println(taskManager.getHistory());
        System.out.println("\n");
        taskManager.getSubtask(3, 1);
        System.out.println(taskManager.getHistory());
        System.out.println("\n");

//        Меняем стаус задачи Task на DONE
        System.out.println("------------------------------------------------------------");
        System.out.println("Статус задачи должен поменяться на DONE");
        int newTaskId = 1;
        Task newTask = new Task("Пробежка", "Выйти на пробежку в 10:00", TaskStatus.DONE, newTaskId);
        taskManager.changeTaskStatus(newTask);
        System.out.println(taskManager.getTask(1));

//        Меняем подзадачу (1 задача IN_PROGRESS, 2 NEW)
        System.out.println("------------------------------------------------------------");
        System.out.println("Статус эпика должен поменяться на IN_PROGRESS");
        int newEpic1Id = 2;
        Epic newEpic2 = new Epic("Сделать ТЗ", "Выполнить все, что нужно", TaskStatus.NEW, twoSubtasksOneInProgress(), newEpic1Id);
        newEpic2.setIdToSubtasks();
        taskManager.changeEpicStatus(newEpic2);
        System.out.println(taskManager.getTask(2));

//        Меняем подзадачу (единственная подзадача становится DONE)
        System.out.println("------------------------------------------------------------");
        System.out.println("Статус эпика должен поменяться на DONE");
        int newEpic2Id = 3;
        Epic newEpic3 = new Epic("Сделать тесты", "Придумать тесты", TaskStatus.NEW, oneDoneSubtask(), newEpic2Id);
        newEpic3.setIdToSubtasks();
        taskManager.changeEpicStatus(newEpic3);
        System.out.println(taskManager.getTask(3));

        System.out.println("------------------------------------------------------------");
        taskManager.removeTask(1);
        System.out.println(taskManager.getTasks());

//        Удаляем подзадачу IN_PROGRESS, остается одна задача NEW, статус эпика должен обратно вернуться на NEW
        System.out.println("------------------------------------------------------------");
        System.out.println("Статус эпика должен поменяться на NEW");
        taskManager.removeSubtask(2, 1);
        System.out.println(taskManager.getTasks());

        System.out.println("------------------------------------------------------------");
        taskManager.clearTasks();
        System.out.println(taskManager.getTasks());

    }

//    Набор методов для удобства тетсирования
    private static ArrayList<Subtask> twoNewSubtasks() {
        Subtask subtask1 = new Subtask("Прочитать ТЗ", "Просмотреть все пункты", TaskStatus.NEW);
        Subtask subtask2 = new Subtask("Написать программу", "Реализовать методы и классы", TaskStatus.NEW);
//        Subtask subtask1 = new Subtask("Прочитать ТЗ", "Просмотреть все пункты", TaskStatus.DONE);
//        Subtask subtask2 = new Subtask("Написать программу", "Реализовать методы и классы", TaskStatus.DONE);
//        Subtask subtask1 = new Subtask("Прочитать ТЗ", "Просмотреть все пункты", TaskStatus.IN_PROGRESS);
//        Subtask subtask2 = new Subtask("Написать программу", "Реализовать методы и классы", TaskStatus.DONE);
        ArrayList<Subtask> subtasks = new ArrayList<>();
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }

    private static ArrayList<Subtask> twoSubtasksOneInProgress() {
        Subtask subtask1 = new Subtask("Прочитать ТЗ", "Просмотреть все пункты", TaskStatus.IN_PROGRESS);
        Subtask subtask2 = new Subtask("Написать программу", "Реализовать методы и классы", TaskStatus.NEW);
        ArrayList<Subtask> subtasks = new ArrayList<>();
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }

    private static ArrayList<Subtask> oneNewSubtask() {
        Subtask subtask1 = new Subtask("Прочитать ТЗ", "Просмотреть все пункты", TaskStatus.NEW);
        ArrayList<Subtask> subtasks = new ArrayList<>();
        subtasks.add(subtask1);
        return subtasks;
    }

    private static ArrayList<Subtask> oneDoneSubtask() {
        Subtask subtask1 = new Subtask("Прочитать ТЗ", "Просмотреть все пункты", TaskStatus.DONE);
        ArrayList<Subtask> subtasks = new ArrayList<>();
        subtasks.add(subtask1);
        return subtasks;
    }
}
