package com.highpeak.chat.uiresponse;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Generic response.
 *
 * @author Guruprasad A N on 19/1/18
 */

public class GenericResponse {
    private String message;
    private String error;

    /**
     * Instantiates a new Generic response.
     *
     * @param message the message
     */
    public GenericResponse(final String message) {
        super();
        this.message = message;
    }

    /**
     * Instantiates a new Generic response.
     *
     * @param message the message
     * @param error   the error
     */
    public GenericResponse(final String message, final String error) {
        super();
        this.message = message;
        this.error = error;
    }

    /**
     * Instantiates a new Generic response.
     *
     * @param allErrors the all errors
     * @param error     the error
     */
    public GenericResponse(List<ObjectError> allErrors, String error) {
        this.error = error;
        String temp = allErrors.stream()
                .map(e -> {
                    if (e instanceof FieldError) {
                        return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
                    } else {
                        return "{\"object\":\"" + e.getObjectName() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
                    }
                })
                .collect(Collectors.joining(","));
        this.message = "[" + temp + "]";
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(final String error) {
        this.error = error;
    }

}
