package it.sevenbits.spring.core.repository;

import it.sevenbits.spring.core.model.Task;

import java.util.List;

/**
 * Interface for TasksRepository
 */
public interface ITaskRepository {
    /**
     * add task to repository
     * @param task task
     * @return returns mined class
     */
    Task addTask(Task task);

    /**
     * returns the entire list of tasks
     * @return Task list
     */
    List<Task> getAllTask();
}
