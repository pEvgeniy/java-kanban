package service.managers;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TaskManager {

    List<Task> getHistory();

    HashMap<Integer, Task> getTasks();

    Task getTask(Integer id);

    Subtask getSubtask(Integer epicId, Integer subtaskId);

    ArrayList<Subtask> getEpicSubtasks(Integer epicId);

    void clearTasks();

    void addTask(Task task);

    void updateTask(Integer id, Task task);

    void updateEpic(Integer epicId, Epic epic);

    void removeTask(Integer id);

    void removeSubtask(Integer epicId, Integer subtaskId);

    void changeTaskStatus(Task task);

    void changeEpicStatus(Epic epic);

}
