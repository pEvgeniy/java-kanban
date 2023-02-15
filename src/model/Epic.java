package model;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task{
    private final ArrayList<Subtask> subtasks;

    public Epic(String name, String description, TaskStatus taskStatus, ArrayList<Subtask> subtasks) {
        super(name, description, taskStatus);
        this.subtasks = subtasks;
    }

    public Epic(String name, String description, TaskStatus taskStatus, ArrayList<Subtask> subtasks, Integer id) {
        super(name, description, taskStatus, id);
        this.subtasks = subtasks;
        this.id = id;
    }

    public Epic(String name, String description, ArrayList<Subtask> subtasks) {
        super(name, description);
        this.subtasks = subtasks;
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
        if (subtasks.isEmpty()) {
            return true;
        }
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
            if (!subtask.getTaskStatus().equals(TaskStatus.DONE)) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return id == epic.id && Objects.equals(subtasks, epic.subtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, subtasks);
    }
}
