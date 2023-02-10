import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import Tasks.TaskStatus;

import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Object> tasks = new HashMap<>();
    private Integer uniqueId = 0;

    public HashMap<Integer, Object> getTasks() {
        return tasks;
    }

    public void clearTasks() {
        System.out.println("\nЗадачи удалены.");
        tasks.clear();
    }

    public Object getTask(Integer id) {
        return tasks.get(id);
    }

    public void addTask(Object task) {
        Integer id = uniqueId++;
        if (task.getClass().equals(Task.class)) {
            System.out.println("\nЗадача " + ((Task) task).getName() + " добавлена.");
            ((Task) task).setId(id);
        } else if (task.getClass().equals(Epic.class)) {
            System.out.println("\nЗадача " + ((Epic) task).getName() + " добавлена.");
            ((Epic) task).setId(id);
            ((Epic) task).setIdToSubtasks();
        }
        tasks.put(id, task);
    }

    public void updateTask(Integer id, Object task) {
        if (task.getClass().equals(Task.class)) {
            System.out.println("\nЗадача " + ((Task) task).getName() + " обновлена.");
        } else if (task.getClass().equals(Epic.class)) {
            System.out.println("\nЗадача " + ((Epic) task).getName() + " обновлена.");
        }
        tasks.put(id, task);
    }

    public void removeTask(Integer id) {
        if (tasks.get(id).getClass().equals(Task.class)) {
            System.out.println("\nЗадача " + ((Task) tasks.get(id)).getName() + " удалена.");
        } else if (tasks.get(id).getClass().equals(Epic.class)) {
            System.out.println("\nЗадача " + ((Epic) tasks.get(id)).getName() + " удалена.");
        }
        tasks.remove(id);
    }

    public List<Subtask> getEpicSubTasks(Integer id) {
        Object epic = tasks.get(id);
        if (!epic.getClass().equals(Epic.class)) return null;
        return ((Epic) epic).getSubtasks();
    }

    public void changeTaskStatus(Integer epicId, Integer subTaskId, TaskStatus taskStatus) {
        Object task = tasks.get(epicId);

        if (task.getClass().equals(Task.class)) {
            System.out.println("\nИзменен статус задачи " + ((Task) task).getName()
                    + " с " + ((Task) task).getTaskStatus() + " на " + taskStatus);
            ((Task) task).setTaskStatus(taskStatus);
            tasks.put(epicId, task);
            return;
        } else if (task.getClass().equals(Epic.class)) {
            for (Subtask subtask : ((Epic) task).getSubtasks()) {
                if (subtask.getId() == subTaskId) {
                    System.out.println("\nИзменен статус подзадачи " + subtask.getName()
                            + " задачи " + ((Epic) task).getName()
                            + " с " + subtask.getTaskStatus() + " на " + taskStatus);
                    subtask.setTaskStatus(taskStatus);
                    if (((Epic) task).isEnded()) {
                        ((Epic) task).setTaskStatus(TaskStatus.DONE);
                    }
                    return;
                }
            }
        }
        throw new RuntimeException("No such task found");
    }

}
