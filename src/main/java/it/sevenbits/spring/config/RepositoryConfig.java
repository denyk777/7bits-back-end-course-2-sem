package it.sevenbits.spring.config;

import it.sevenbits.spring.core.repository.DatabaseTasksRepository;
import it.sevenbits.spring.core.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

@Configuration
public class RepositoryConfig {
    @Bean
    public ITaskRepository tasksRepository(
            @Qualifier("tasksJdbcOperations") JdbcOperations jdbcOperations) {
        return new DatabaseTasksRepository(jdbcOperations);
    }
}
