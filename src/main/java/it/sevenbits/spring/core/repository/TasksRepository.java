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
    public boolean updateClass(final String id, final String newText) {
        for (Task item : tasks) {
            if (item.getId().equals(id)) {
                item.setText(newText);
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
