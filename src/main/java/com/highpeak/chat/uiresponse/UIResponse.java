package com.highpeak.chat.uiresponse;

import java.io.Serializable;

/**
 * The type Ui response.
 *
 * @param <T> the type parameter
 * @author Guruprasad A N on 19/1/18
 */

public class UIResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient T entity;
    private Integer status;
    private String message;
    /**
     * Instantiates a new Ui response.
     *
     * @param entity the entity
     */
    public UIResponse(T entity) {
        this.entity = entity;
    }

    /**
     * No arg constructor
     */
    public UIResponse() {
        super();
    }

    /**
     * Gets entity.
     *
     * @return the entity
     */
    public T getEntity() {
        return entity;
    }

    /**
     * Sets entity.
     *
     * @param entity the entity
     */
    public void setEntity(T entity) {
        this.entity = entity;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Integer status) {
        this.status = status;
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
    public void setMessage(String message) {
        this.message = message;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

}