package model;

public class Subtask extends Task{
    private int epicId;

    public Subtask(String name, String description, TaskStatus taskStatus) {
        super(name, description, taskStatus);
    }
    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
