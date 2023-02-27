package service.managers;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryHistoryManager implements HistoryManager{
    private final List<Task> historyList = new ArrayList<>(10);

    @Override
    public void add(Task task) {
        if (historyList.size() == 10) {
            historyList.remove(0);
            historyList.add(historyList.size() - 1, task);
            return;
        }
        historyList.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }

    @Override
    public String toString() {
        return "InMemoryHistoryManager{" +
                "historyList=" + historyList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryHistoryManager that = (InMemoryHistoryManager) o;
        return Objects.equals(historyList, that.historyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyList);
    }
}
