package it.sevenbits.spring.web.controllers;

import it.sevenbits.spring.web.models.CreateStatusRequest;
import it.sevenbits.spring.web.models.CreateTaskRequest;
import it.sevenbits.spring.core.models.Task;
import it.sevenbits.spring.core.repository.ITaskRepository;
import it.sevenbits.spring.web.models.CreateTaskTextRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import javax.validation.Valid;
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
    private ResponseEntity<List<Task>> getTask(final @RequestParam(value = "status", required = false,
            defaultValue = "inbox") String status) {
        if (status != null) {
            if (CreateStatusRequest.getStatus(status) != null)  {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(repository.getTaskByStatus(status));
            } else  {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * @param taskText task
     * @return created task
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTask(final @RequestBody @Valid CreateTaskTextRequest taskText) {
        try {
            Task newTask = repository.addTask(taskText);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Location", "/task/" + newTask.getId());
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8)
                    .headers(headers).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Task> getTaskById(final @PathVariable("taskId") String id) {
        Task task = repository.getTaskById(id);
        if (task != null) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PATCH)
    @ResponseBody
    private ResponseEntity<String> updateTask(final @PathVariable("taskId") String id,
                                      final @RequestBody CreateTaskRequest jsonArgument) {
        if (repository.getTaskById(id) != null) {
            if (jsonArgument.getText() != null) {
                if (jsonArgument.validText() != null) {
                    repository.updateText(id, jsonArgument.getText());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }
            if (jsonArgument.getStatus() != null) {
                if (jsonArgument.validStatus() != null) {
                    repository.updateStatus(id, jsonArgument.getStatus());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    private ResponseEntity updateTask(final @PathVariable("taskId") String id) {
        try {
            if (repository.deleteTask(id)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
