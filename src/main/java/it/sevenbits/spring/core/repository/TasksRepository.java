package it.sevenbits.spring.core.repository;

import it.sevenbits.spring.core.models.Task;
import it.sevenbits.spring.web.models.CreateTaskTextRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Contains task list
 */
public class TasksRepository implements ITaskRepository {
    private List<Task> tasks = new ArrayList<>();

    /**
     * default constructor
     */
    public TasksRepository() {}

    /**
     *
     * @param task task
     * @return created task
     */
    public Task addTask(final CreateTaskTextRequest task) {
        Task newTask = new Task(UUID.randomUUID().toString(), task.getTaskText(), "inbox");
        tasks.add(newTask);
        return newTask;
    }

    public List<Task> getAllTask() {
        return Collections.unmodifiableList(tasks);
    }

    @Override
    public List<Task> getTaskByStatus(String status) {
        List<Task> statusListTask = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getStatus().equals(status)) {
                statusListTask.add(task);
            }
        }
        return statusListTask;
    }

    @Override
    public Task getTaskById(final String id) {
        for (Task item : tasks) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean updateText(final String id, final String newText) {
        for (Task item : tasks) {
            if (item.getId().equals(id)) {
                item.setText(newText);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateStatus(final String id, final String newStatus ) {
        for (Task item : tasks) {
            if (item.getId().equals(id)) {
                item.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTask(final String id) {
        for (Task item : tasks) {
            if (item.getId().equals(id)) {
                tasks.remove(item);
                return true;
            }
        }
        return false;
    }
}
