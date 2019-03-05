package it.sevenbits.courses.servlet.servlets;

import it.sevenbits.courses.servlet.Task;

import java.util.concurrent.CopyOnWriteArrayList;

public class TasksRepository {
    private static TasksRepository instance;
    private CopyOnWriteArrayList<Task> task;

    private TasksRepository() {
        Task one = new Task("first");
        task = new CopyOnWriteArrayList<Task>();
        task.add(one);
    }

    public static TasksRepository getInstance() {
        if (instance == null) {
            instance = new TasksRepository();
        }
        return instance;
    }

    public void addTask(Task task) {
        this.task.add(task);
    }

    public Task getTask(int id) {
        return task.get(id);
    }

    public int getSize() {
        return task.size();
    }

    public void deleteByID(int ID) {
        task.remove(ID);
    }
}
