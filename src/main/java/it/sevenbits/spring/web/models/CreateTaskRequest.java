package it.sevenbits.spring.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Take json argument for patch
 */
public class CreateTaskRequest {
    private String text;
    private String status;

    /**
     * @param text in json string
     */
    @JsonCreator
    public CreateTaskRequest(final @JsonProperty("newText") String text, final @JsonProperty("status") String status) {
        this.text = text;
        this.status = status;
    }

    public String getStatus() {
        if (status.equals("inbox") || status.equals("done")) {
            return status;
        } else {
            return null;
        }
    }

    public String getText() {
        return text;
    }
}
