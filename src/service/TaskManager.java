package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private int uniqueId = 0;

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public Task getTask(Integer id) {
        if (checkForTaskClass(id) || checkForEpicClass(id)) {
            return tasks.get(id);
        } else {
            throw new RuntimeException("Wrong type of task (should be Task.Class or Epic.Class)");
        }
    }

    public ArrayList<Subtask> getEpicSubtasks(Integer epicId) {
        if (checkForEpicClass(epicId)) {
            return ((Epic) tasks.get(epicId)).getSubtasks();
        } else {
            throw new RuntimeException("Wrong epicId of task (should be id of Epic.Class)");
        }
    }

    public void clearTasks() {
        System.out.println("\nЗадачи удалены.");
        tasks.clear();
    }

    public void addTask(Task task) {
        int id = uniqueId++;
        if (checkForEpicClass(task)) {
            ((Epic) task).setIdToSubtasks();
        }
        task.setId(id);
        System.out.println("\nЗадача " + task.getName() + " добавлена.");
        if (checkForEpicClass(task)) {
            updateEpic(task.getId(), (Epic) task);
        } else {
            tasks.put(id, task);
        }
    }

    private void updateTask(Integer id, Task task) {
        System.out.println("\nЗадача " + task.getName() + " обновлена.");
        tasks.put(id, task);
    }

    private void updateEpic(Integer epicId, Epic epic) {
        if (epic.isNew()) {
            tasks.put(epicId , new Epic(epic.getName(), epic.getDescription(), TaskStatus.NEW, epic.getSubtasks()));
            System.out.println("\nСтатус задачи " + epic.getName() + " обновлен на NEW.");
        } else if (epic.isEnded()) {
            tasks.put(epicId , new Epic(epic.getName(), epic.getDescription(), TaskStatus.DONE, epic.getSubtasks()));
            System.out.println("\nСтатус задачи " + epic.getName() + " обновлен на DONE.");
        } else if (epic.isInProgress()) {
            tasks.put(epicId , new Epic(epic.getName(), epic.getDescription(), TaskStatus.IN_PROGRESS, epic.getSubtasks()));
            System.out.println("\nСтатус задачи " + epic.getName() + " обновлен на IN_PROGRESS.");
        }
    }

    public void removeTask(Integer id) {
        if (tasks.remove(id) != null) {
            System.out.println("\nЗадача удалена.");
        } else {
            throw new RuntimeException("No such element in Task Map");
        }
    }

    public void removeSubtask(Integer epicId, Integer subtaskId) {
        ArrayList<Subtask> subtaskList = ((Epic) tasks.get(epicId)).getSubtasks();
        Subtask subtaskToBeDeleted = subtaskList.get(subtaskId);
        if (subtaskList.remove(subtaskToBeDeleted)) {
            System.out.println("\nЗадача удалена.");
            Epic epic = (Epic) tasks.get(epicId);
            updateEpic(epicId, epic);
        } else {
            throw new RuntimeException("No such element in Subtask List");
        }
    }

    public void changeTaskStatus(Task task) {
        if (checkForTaskClass(task.getId())) {
            updateTask(task.getId(), task);
            return;
        }
        throw new RuntimeException("Wrong type of Task");
    }

    public void changeEpicStatus(Epic epic) {
        if (checkForEpicClass(epic.getId())) {
            updateEpic(epic.getId() , epic);
            return;
        }
        throw new RuntimeException("Wrong type of Task");
    }

    private boolean checkForTaskClass(Integer id) {
        Object task = tasks.get(id);
        return task.getClass().equals(Task.class);
    }

    private boolean checkForTaskClass(Object task) {
        return task.getClass().equals(Task.class);
    }

    private boolean checkForEpicClass(Integer id) {
        Object epic = tasks.get(id);
        return epic.getClass().equals(Epic.class);
    }

    private boolean checkForEpicClass(Object task) {
        return task.getClass().equals(Epic.class);
    }
}
