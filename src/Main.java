import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

//        Создаем задачу task1 и добавляем
        Task task1 = new Task("Пробежка", "Выйти на пробежку в 10:00", TaskStatus.NEW);
        taskManager.addTask(task1);

//        Создаем 2 подзадачи с TaskStatus.NEW и добавляем в созданный эпик epic1
        Epic epic1 = new Epic("Сделать ТЗ", "Выполнить все, что нужно", twoNewSubtasks());
        taskManager.addTask(epic1);

//        Создаем 1 подзадачу с TaskStatus.NEW и добавляем в созданный эпик epic2
        Epic epic2 = new Epic("Сделать тесты", "Придумать тесты", oneNewSubtask());
        taskManager.addTask(epic2);

//        Выводим все задачи
        System.out.println(taskManager.getTasks());
        System.out.println("\n");

//        Выводим каждую задачу по одной
        System.out.println(taskManager.getTask(0));
        System.out.println(taskManager.getTask(1));
        System.out.println(taskManager.getTask(2));
        System.out.println("\n");

//        Выводим подзадачи эпиков
        System.out.println(taskManager.getEpicSubtasks(1));
        System.out.println(taskManager.getEpicSubtasks(2));

//        Меняем стаус задачи Task на DONE
        System.out.println("------------------------------------------------------------");
        System.out.println("Статус задачи должен поменяться на DONE");
        int newTaskId = 0;
        Task newTask = new Task("Пробежка", "Выйти на пробежку в 10:00", TaskStatus.DONE, newTaskId);
        taskManager.changeTaskStatus(newTask);
        System.out.println(taskManager.getTask(0));

//        Меняем подзадачу (1 задача IN_PROGRESS, 2 NEW)
        System.out.println("------------------------------------------------------------");
        System.out.println("Статус эпика должен поменяться на IN_PROGRESS");
        int newEpic1Id = 1;
        taskManager.changeEpicStatus(new Epic("Сделать ТЗ", "Выполнить все, что нужно", TaskStatus.NEW, twoSubtasksOneInProgress(), newEpic1Id));
        System.out.println(taskManager.getTask(1));

//        Меняем подзадачу (единственная подзадача становится DONE)
        System.out.println("------------------------------------------------------------");
        System.out.println("Статус эпика должен поменяться на DONE");
        int newEpic2Id = 2;
        taskManager.changeEpicStatus(new Epic("Сделать тесты", "Придумать тесты", TaskStatus.NEW, oneDoneSubtask(), newEpic2Id));
        System.out.println(taskManager.getTask(2));

        System.out.println("------------------------------------------------------------");
        taskManager.removeTask(0);
        System.out.println(taskManager.getTasks());

//        Удаляем подзадачу IN_PROGRESS, остается одна задача NEW, статус эпика должен обратно вернуться на NEW
        System.out.println("------------------------------------------------------------");
        System.out.println("Статус эпика должен поменяться на NEW");
        taskManager.removeSubtask(1, 0);
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
