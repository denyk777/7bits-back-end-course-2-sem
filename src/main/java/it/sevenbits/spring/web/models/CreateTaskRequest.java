package it.sevenbits.spring.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Take json argument for patch
 */
public class CreateTaskRequest {
    private String newText;
    private String status;

    /**
     * @param newText in json string
     */
    @JsonCreator
    public CreateTaskRequest(final @JsonProperty(required = false) String newText, final @JsonProperty(required = false) String status) {
        this.newText = newText;
        this.status = status;
    }

    public int getStatusState() {
        if ((validStatus(status) != null) && (newText != null)) {
            return 0;
        } else if (validStatus(status) != null && newText == null){
            return 1;
        } else if (validStatus(status) == null && newText != null) {
            return 2;
        } else {
            return 4;
        }
    }

    String validStatus(String status) {
        try {
            if (status.equals("inbox") || status.equals("done")) {
                return status;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String getText() {
        return newText;
    }

    public String getStatus() {
        return status;
    }
}
