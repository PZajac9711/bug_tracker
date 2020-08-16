package org.bugtracker.bugtracker.model.exceptions;

import org.springframework.http.HttpStatus;

public class ApiError {
    private String message;
    private String debugMessage;
    private HttpStatus statusCode;

    public ApiError(HttpStatus statusCode, Throwable exception) {
        this.statusCode = statusCode;
        this.message = "Unexpected error";
        this.debugMessage = exception.getMessage();
    }

    public ApiError(HttpStatus statusCode, String message, Throwable exception) {
        this.message = message;
        this.statusCode = statusCode;
        this.debugMessage = exception.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
