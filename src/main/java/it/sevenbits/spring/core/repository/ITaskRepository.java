package it.sevenbits.spring.core.repository;

import it.sevenbits.spring.core.models.Task;
import it.sevenbits.spring.web.models.CreateTaskTextRequest;

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
    Task addTask(CreateTaskTextRequest task);

    /**
     * returns the entire list of tasks
     * @return Task list
     */
    List<Task> getAllTask();

    /**
     * @param status field for filtered
     * @return filtered list tasks
     */
    List<Task> getTaskByStatus(String status);

    /**
     * @param id task
     * @return task
     */
    Task getTaskById(String id);

    /**
     * @param id task
     * @param newText rewrite text task
     * @return function execution status
     */
    boolean updateText(String id, String newText);

    /**
     * @param id task
     * @param newStatus rewrite status task
     * @return function execution status
     */
    boolean updateStatus(String id, String newStatus);

    /**
     * @param id task
     * @return function execution status
     */
    boolean deleteTask(String id);

}
