package it.sevenbits.spring.core.repository;

import it.sevenbits.spring.core.models.Task;
import it.sevenbits.spring.web.models.CreateTaskTextRequest;
import org.springframework.jdbc.core.JdbcOperations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * repository on PostgreSQL
 */
public class DatabaseTasksRepository implements ITaskRepository {
    private JdbcOperations jdbcOperations;


    /**
     * @param jdbcOperations create
     */
    public DatabaseTasksRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Task addTask(final CreateTaskTextRequest task) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssXXX");
        String uuid = UUID.randomUUID().toString();
        String text = task.getTaskText();
        String status = "inbox";
        String createAt = dateFormat.format(new Date());
        int rows = jdbcOperations.update(
                "INSERT INTO tasks (id, text, status, createat) VALUES (?, ?, ?, ?)",
                uuid, text, status, createAt
        );
        return new Task(uuid, text, status, createAt);
    }

    @Override
    public List<Task> getAllTask() {
        return null;
    }

    @Override
    public List<Task> getTaskByStatus(final String status1) {
        return jdbcOperations.query(
                "SELECT id, text, status, createat FROM tasks",
                (resultSet, i) -> {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("text");
                    String status = resultSet.getString("status");
                    String createAt = resultSet.getString("createat");
                    return new Task(id, name, status, createAt);
                });
    }

    @Override
    public Task getTaskById(final String id) {
        return null;
    }

    @Override
    public boolean updateText(final String id, final String newText) {
        return false;
    }

    @Override
    public boolean updateStatus(final String id, final String newStatus) {
        return false;
    }

    @Override
    public boolean deleteTask(final String id) {
        return false;
    }
}
