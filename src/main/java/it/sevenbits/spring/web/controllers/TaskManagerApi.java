package it.sevenbits.spring.web.controllers;

import it.sevenbits.spring.core.model.GeterJsonArgument;
import it.sevenbits.spring.core.model.Task;
import it.sevenbits.spring.core.repository.ITaskRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Task management class with API
 */
@Controller
@RequestMapping(value = "/tasks")
public class TaskManagerApi {
    private ITaskRepository repository;
    private static final int STATUS_OK = 200;
    private static final int STATUS_PATCH_OK = 204;
    private static final int STATUS_INVALID_INPUT = 400;
    private static final int STATUS_NOT_FOUND = 404;


    /**
     * @param repository initialization repository
     */
    public TaskManagerApi(final ITaskRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Task> getTaskById(final @PathVariable("taskId") String id) {
        Task task = repository.getTaskById(id);
        if (task != null) {
            return ResponseEntity.status(STATUS_OK).contentType(MediaType.APPLICATION_JSON).body(task);
        } else {
            return ResponseEntity.status(STATUS_NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/{taskId}", method = RequestMethod.PATCH)
    @ResponseBody
    private ResponseEntity updateTask(final @PathVariable("taskId") String id, final  @RequestBody GeterJsonArgument jsonArgument) {
        try {
            if (repository.updateClass(id, jsonArgument.getText())) {
                return ResponseEntity.status(STATUS_PATCH_OK).build();
            } else {
                return ResponseEntity.status(STATUS_NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(STATUS_INVALID_INPUT).build();
        }
    }

    @RequestMapping(value = "/{taskId}", method = RequestMethod.DELETE)
    @ResponseBody
    private ResponseEntity updateTask(final @PathVariable("taskId") String id) {
        try {
            if (repository.deleteTask(id)) {
                return ResponseEntity.status(STATUS_OK).build();
            } else {
                return ResponseEntity.status(STATUS_NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(STATUS_INVALID_INPUT).build();
        }
    }
}
