package it.sevenbits.spring.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CreateTaskTextRequest {
    @NotBlank
    private String taskText;

    public String getTaskText() {
        return taskText;
    }

    @JsonCreator
    CreateTaskTextRequest(final @JsonProperty("text") String taskText) {
        this.taskText = taskText;
    }
}
