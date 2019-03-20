package it.sevenbits.spring.config;

import it.sevenbits.spring.core.repository.ITaskRepository;
import it.sevenbits.spring.core.repository.TasksRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Repository configuration
 */
@Configuration
public class RepositoryConfig {
    /**
     * @return created bean this type
     */
    @Bean
    ITaskRepository taskRepository() {
        return new TasksRepository();
    }
}
