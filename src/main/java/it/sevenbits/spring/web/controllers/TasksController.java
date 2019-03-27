package it.sevenbits.spring.web.controllers;

import it.sevenbits.spring.web.models.CreateTaskRequest;
import it.sevenbits.spring.core.models.Task;
import it.sevenbits.spring.core.repository.ITaskRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manages requests
 */
@Controller
public class TasksController {
    private ITaskRepository repository;
    private static final int STATUS_GET_ERROR = 403;
    private static final int STATUS_POST_OK = 201;
    private static final int STATUS_POST_ERROR = 400;
    /**
     * transmit Bean in constructor
     * @param taskRepository task repository interface
     */
    public TasksController(final ITaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    /**
     * @return list task
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<List<Task>> getTask() {
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(repository.getAllTask());
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

    private static final int STATUS_PATCH_OK = 204;
    private static final int STATUS_INVALID_INPUT = 400;

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Task> getTaskById(final @PathVariable("taskId") String id) {
        Task task = repository.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PATCH)
    @ResponseBody
    private ResponseEntity updateTask(final @PathVariable("taskId") String id, final  @RequestBody CreateTaskRequest jsonArgument) {
        try {
            if (repository.updateClass(id, jsonArgument.getText())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(STATUS_INVALID_INPUT).build();
        }
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    private ResponseEntity updateTask(final @PathVariable("taskId") String id) {
        try {
            if (repository.deleteTask(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(STATUS_INVALID_INPUT).build();
        }
    }
}
