import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.TaskStatus;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Пробежка", "Выйти на пробежку в 10:00");
        taskManager.addTask(task1);

        Subtask subtask1 = new Subtask("Прочитать ТЗ", "Просмотреть все пункты");
        Subtask subtask2 = new Subtask("Написать программу", "Реализовать методы и классы");
        ArrayList<Subtask> subtasks = new ArrayList<>();
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        Epic epic1 = new Epic("Сделать ТЗ", "Выполнить все, что нужно", subtasks);
        taskManager.addTask(epic1);

        Subtask subtask3 = new Subtask("Подумать над тестами", "Реализовать то, что придумал");
        ArrayList<Subtask> subtasks2 = new ArrayList<>();
        subtasks2.add(subtask3);
        Epic epic2 = new Epic("Сделать тесты", "Придумать тесты", subtasks2);
        taskManager.addTask(epic2);

        System.out.println(taskManager.getTasks());

        System.out.println(taskManager.getTask(0));
        System.out.println(taskManager.getTask(1));
        System.out.println(taskManager.getTask(2));

        System.out.println(taskManager.getEpicSubTasks(0));
        System.out.println(taskManager.getEpicSubTasks(1));

        taskManager.changeTaskStatus(0, 1, TaskStatus.DONE);
        System.out.println(taskManager.getTask(0));
        taskManager.changeTaskStatus(1, 0, TaskStatus.DONE);
        System.out.println(taskManager.getTask(1));
        taskManager.changeTaskStatus(1, 1, TaskStatus.DONE);
        System.out.println(taskManager.getTask(1));


        taskManager.removeTask(0);
        System.out.println(taskManager.getTasks());

        taskManager.clearTasks();
        System.out.println(taskManager.getTasks());


    }
}
