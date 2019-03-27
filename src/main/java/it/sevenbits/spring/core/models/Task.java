package it.sevenbits.spring.core.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * task class
 */
public class Task {
    private String id;
    private String text;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    /**
     * @param text in task
     */
    @JsonCreator
    public Task(final String id, String text, String status) {
        this.id = id;
        this.text = text;
        this.status = status;
    }

    private Task() {
    }
}
