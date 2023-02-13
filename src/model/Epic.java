package model;

import java.util.ArrayList;

public class Epic extends Task{
    private final String name;
    private final String description;
    private int id;
    private final TaskStatus taskStatus;
    private final ArrayList<Subtask> subtasks;

    public Epic(String name, String description, TaskStatus taskStatus, ArrayList<Subtask> subtasks) {
        super(name, description, taskStatus);
        this.subtasks = subtasks;
        this.name = name;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public Epic(String name, String description, TaskStatus taskStatus, ArrayList<Subtask> subtasks, Integer id) {
        super(name, description, taskStatus, id);
        this.subtasks = subtasks;
        this.name = name;
        this.description = description;
        this.taskStatus = taskStatus;
        this.id = id;
    }

    public void setIdToSubtasks() {
        int id = 0;
        for (Subtask subtask : subtasks) {
            subtask.setEpicId(this.id);
            subtask.setId(id);
            id++;
        }
    }

    public boolean isNew() {
        int isNewCounter = 0;
        for (Subtask subtask : subtasks) {
            if (subtask.getTaskStatus().equals(TaskStatus.NEW)) {
                isNewCounter++;
            }
        }
        return isNewCounter == subtasks.size();
    }

    public boolean isInProgress() {
        int inProgressCounter = 0;
        for (Subtask subtask : subtasks) {
            if (subtask.getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
                inProgressCounter++;
            }
        }
        return inProgressCounter > 0;
    }

    public boolean isEnded() {
        int doneCounter = 0;
        for (Subtask subtask : subtasks) {
            if (subtask.getTaskStatus().equals(TaskStatus.DONE)) {
                doneCounter++;
            }
        }
        return doneCounter == subtasks.size();
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", taskStatus=" + taskStatus +
                ", subtasks=" + subtasks +
                '}';
    }
}
