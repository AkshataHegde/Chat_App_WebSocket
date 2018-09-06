package com.highpeak.chat.exception;

import org.springframework.http.HttpStatus;


public class DataException extends Exception {

    private static final long serialVersionUID = 8393688636580014922L;

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
    private final String devErrorMessage;

    /**
     * Instantiates a new Data exception.
     *
     * @param errorCode
     *            the error code
     * @param errorMessage
     *            the error message
     * @param httpStatus
     *            the http status
     */
    public DataException(String errorCode, String errorMessage, HttpStatus httpStatus )
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.devErrorMessage = null;
    }

    public DataException(String errorCode, String errorMessage, String devErrorMessage, HttpStatus httpStatus )
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.devErrorMessage = devErrorMessage;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public String getErrorCode()
    {
        return errorCode;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * Gets http status.
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus()
    {
        return httpStatus;
    }

    public String getDevErrorMessage()
    {
        return devErrorMessage;
    }

}