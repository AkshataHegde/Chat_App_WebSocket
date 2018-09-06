package com.highpeak.chat.uiresponse;

import java.io.Serializable;

/**
 * The type Ui error message.
 *
 * @param <T> the type parameter
 * @author Guruprasad A N on 19/1/18
 */

public class UIErrorMessage<T> extends UIResponse<T> implements Serializable {

    private static final long serialVersionUID = 4311696013200578760L;
    private String messageCode;
    private String message;
    private Integer status;
    private String stackTrace;
    private String devMessage;

    /**
     * Instantiates a new Ui error message.
     *
     * @param t the t
     */
    public UIErrorMessage(T t) {
        super(t);
    }

    /**
     * No arg constructor
     */
    public UIErrorMessage() {
        super();
    }

    @Override
    public String toString() {
        return "UIErrorMessage [messageCode=" + messageCode + ", message=" + message + ", stackTrace=" + stackTrace
                + "]";
    }

    /**
     * Gets message code.
     *
     * @return the message code
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * Sets message code.
     *
     * @param messageCode the message code
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    /**
     * Gets stack trace.
     *
     * @return the stack trace
     */
    public String getStackTrace() {
        return stackTrace;
    }

    /**
     * Sets stack trace.
     *
     * @param stackTrace the stack trace
     */
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}