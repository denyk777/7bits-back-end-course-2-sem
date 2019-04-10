package it.sevenbits.spring.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

/**
 * model for checking text field
 */
public class CreateTaskTextRequest {
    @NotBlank
    private String taskText;

    public String getTaskText() {
        return taskText;
    }

    /**
     * @param taskText contain provided text
     */
    @JsonCreator
    CreateTaskTextRequest(final @JsonProperty("text") String taskText) {
        this.taskText = taskText;
    }
}
