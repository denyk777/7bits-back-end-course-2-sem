package it.sevenbits.spring.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Take json argument for patch
 */
public class CreateTaskRequest {
    private String text;

    /**
     * @param text in json string
     */
    @JsonCreator
    public CreateTaskRequest(final @JsonProperty("text") String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
