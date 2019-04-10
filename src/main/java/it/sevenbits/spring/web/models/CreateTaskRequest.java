package it.sevenbits.spring.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Take json argument for patch
 */
public class CreateTaskRequest {
    private String text;
    private String status;

    /**
     * @param text in json string
     * @param status in json string
     */
    @JsonCreator
    public CreateTaskRequest(final @JsonProperty(required = false) String text, final @JsonProperty(required = false) String status) {
        this.text = text;
        this.status = status;
    }


    /**
     * @return validation of the text field
     */
    public String validText() {
        if (!this.text.isEmpty() && !this.text.trim().isEmpty()) {
            return this.text;
        } else {
            return null;
        }
    }

    /**
     * @return validation of the status field
     */
    public String validStatus() {
        try {
            if (this.status.equals("inbox") || this.status.equals("done")) {
                return this.status;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String getText() {
        return text;
    }

    public String getStatus() {
        return this.status;
    }
}
