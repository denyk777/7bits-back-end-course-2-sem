package it.sevenbits.spring.web.controllers;

import it.sevenbits.spring.web.models.CreateTaskRequest;
import it.sevenbits.spring.core.models.Task;
import it.sevenbits.spring.core.repository.ITaskRepository;
import it.sevenbits.spring.web.models.CreateTaskTextRequest;
import org.springframework.http.HttpStatus;
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
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * @param taskText task
     * @return created task
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Task> addTask(final @RequestBody CreateTaskTextRequest taskText) {
        try {
            Task newTask = repository.addTask(taskText);
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(newTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Task> getTaskById(final @PathVariable("taskId") String id) {
        Task task = repository.getTaskById(id);
        if (task != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PATCH)
    @ResponseBody
    private ResponseEntity updateTask(final @PathVariable("taskId") String id,
                                      final  @RequestBody CreateTaskRequest jsonArgument) {

            boolean updateText = false, updateStatus = false;
            if (jsonArgument.getText() != null) {
                updateText = repository.updateText(id, jsonArgument.getText());
            }
            if (jsonArgument.getStatus() != null) {
                updateStatus = repository.updateStatus(id, jsonArgument.getStatus());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (updateText || updateStatus) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    private ResponseEntity updateTask(final @PathVariable("taskId") String id) {
        try {
            if (repository.deleteTask(id)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
