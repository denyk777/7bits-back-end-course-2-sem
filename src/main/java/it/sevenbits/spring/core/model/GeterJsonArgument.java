package it.sevenbits.spring.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Take json argument for patch
 */
public class GeterJsonArgument {
    private String text;

    /**
     * @param text in json string
     */
    @JsonCreator
    public GeterJsonArgument(final @JsonProperty("text") String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
