package it.sevenbits.spring.core.models;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * task class
 */
public class Task {
    private String id;
    private String text;
    private String status;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    private String createdAt;

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status set status task
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * @return task id
     */
    public String getId() {
        return id;
    }

    /**
     * @param text set text task
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * @param id set id task
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return task text
     */
    public String getText() {
        return text;
    }

    /**
     * @param id in task
     * @param text in task
     * @param status in task
     * @param createdAt task
     */
    @JsonCreator
    public Task(final String id, final String text, final String status, final String createdAt) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.createdAt = createdAt;
    }

    /**
     * default constructor
     */
    private Task() {
    }
}
