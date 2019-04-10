package it.sevenbits.spring.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * Take json argument for patch
 */
@Validated
public class CreateTaskRequest {
    @NotBlank
    private String text;
    private String status;

    /**
     * @param text in json string
     */
    @JsonCreator

    public CreateTaskRequest(final @JsonProperty(required = false) String text, final @JsonProperty(required = false) String status) {
        this.text = text;
        this.status = status;
    }

    public String validText() {
        if (!this.text.isEmpty() && !this.text.trim().isEmpty()) {
            return this.text;
        } else {
            return null;
        }
    }

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
