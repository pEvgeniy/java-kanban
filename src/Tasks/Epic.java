package Tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
    private List<Subtask> subtasks;
    private Integer doneCounter;

    public Epic(String name, String description, ArrayList<Subtask> subtasks) {
        super(name, description);
        this.subtasks = subtasks;
    }

    public void setIdToSubtasks() {
        int id = 0;
        for (Subtask subtask : subtasks) {
            subtask.setId(id);
            id++;
        }
    }

    public boolean isEnded() {
        doneCounter = 0;
        for (Subtask subtask : subtasks) {
            if (subtask.getTaskStatus().equals(TaskStatus.DONE)) {
                doneCounter++;
            }
        }
        return doneCounter == subtasks.size();
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}
