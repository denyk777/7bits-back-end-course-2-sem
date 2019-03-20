package it.sevenbits.spring.core.repository;

import it.sevenbits.spring.core.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains task list
 */
public class TasksRepository implements ITaskRepository {
    private List<Task> tasks = new ArrayList<>();

    /**
     * default constructor
     */
    public TasksRepository() {
        Task task = new Task("init");
        tasks.add(task);
    }

    /**
     *
     * @param task task
     * @return created task
     */
    public Task addTask(final Task task) {
        Task newTask = new Task(task.getText());
        tasks.add(newTask);
        return newTask;
    }

    public List<Task> getAllTask() {
        return Collections.unmodifiableList(tasks);
    }
}
