package service;

import service.managers.InMemoryTaskManager;

public class Managers {

    public InMemoryTaskManager getDefault() {
        return new InMemoryTaskManager();
    }
}
