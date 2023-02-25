package service.managers;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.Managers;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager{
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private int uniqueId = 0;
    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    @Override
    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    @Override
    public Task getTask(Integer id) {
        if (checkForTaskClass(id) || checkForEpicClass(id)) {
            historyManager.add(tasks.get(id));
            return tasks.get(id);
        } else {
            throw new RuntimeException("Wrong type of task (should be Task.Class or Epic.Class)");
        }
    }

    @Override
    public Subtask getSubtask(Integer epicId, Integer subtaskId) {
        if (checkForEpicClass(epicId)) {
            Epic epic = (Epic) tasks.get(epicId);
            Subtask subtask = epic.getSubtasks().get(subtaskId);
            historyManager.add(subtask);
            return subtask;
        } else {
            throw new RuntimeException("Wrong epicId of task (should be id of Epic.Class)");
        }
    }

    @Override
    public ArrayList<Subtask> getEpicSubtasks(Integer epicId) {
        if (checkForEpicClass(epicId)) {
            return ((Epic) tasks.get(epicId)).getSubtasks();
        } else {
            throw new RuntimeException("Wrong epicId of task (should be id of Epic.Class)");
        }
    }

    @Override
    public void clearTasks() {
        System.out.println("\nЗадачи удалены.");
        tasks.clear();
    }

    @Override
    public void addTask(Task task) {
        int id = uniqueId++;
        task.setId(id);
        if (checkForEpicClass(task)) {
            ((Epic) task).setIdToSubtasks();
            updateEpic(task.getId(), (Epic) task);
        } else {
            tasks.put(id, task);
        }
        System.out.println("\nЗадача " + task.getName() + " добавлена.");
    }

    @Override
    public void updateTask(Integer id, Task task) {
        System.out.println("\nЗадача " + task.getName() + " обновлена.");
        tasks.put(id, task);
    }

    @Override
    public void updateEpic(Integer epicId, Epic epic) {
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

    @Override
    public void removeTask(Integer id) {
        if (tasks.remove(id) != null) {
            System.out.println("\nЗадача удалена.");
        } else {
            throw new RuntimeException("No such element in Task Map");
        }
    }

    @Override
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

    @Override
    public void changeTaskStatus(Task task) {
        if (checkForTaskClass(task.getId())) {
            updateTask(task.getId(), task);
            return;
        }
        throw new RuntimeException("Wrong type of Task");
    }

    @Override
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
