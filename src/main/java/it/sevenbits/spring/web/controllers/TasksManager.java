package it.sevenbits.spring.web.controllers;

import it.sevenbits.spring.core.model.Task;
import it.sevenbits.spring.core.repository.ITaskRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Manages requests
 */
@Controller
public class TasksManager {
    private ITaskRepository repository;
    private static final int STATUS_GET_OK = 200;
    private static final int STATUS_GET_ERROR = 403;
    private static final int STATUS_POST_OK = 201;
    private static final int STATUS_POST_ERROR = 400;
    /**
     * transmit Bean in constructor
     * @param taskRepository task repository interface
     */
    public TasksManager(final ITaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    /**
     * @return list task
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<List<Task>> getTask() {
        try {
            return ResponseEntity.status(STATUS_GET_OK).contentType(MediaType.APPLICATION_JSON).body(repository.getAllTask());
        } catch (Exception e) {
            return ResponseEntity.status(STATUS_GET_ERROR).build();
        }
    }

    /**
     * @param task task
     * @return created task
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> addTask(final @RequestBody Task task) {
        try {
            Task newTask = repository.addTask(task);
            return ResponseEntity.status(STATUS_POST_OK).contentType(MediaType.APPLICATION_JSON).body(newTask);
        } catch (Exception e) {
            return ResponseEntity.status(STATUS_POST_ERROR).build();
        }
    }
}
