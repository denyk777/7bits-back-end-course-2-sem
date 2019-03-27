package it.sevenbits.spring.core.repository;

import it.sevenbits.spring.core.models.Task;
import it.sevenbits.spring.web.models.CreateTaskTextRequest;
import org.springframework.http.ResponseEntity;

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

    boolean updateStatus(String id, String newStatus);

    /**
     * @param id task
     * @return function execution status
     */
    boolean deleteTask(String id);

}
