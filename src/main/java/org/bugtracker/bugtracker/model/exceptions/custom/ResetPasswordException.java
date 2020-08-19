package org.bugtracker.bugtracker.model.exceptions.custom;

public class ResetPasswordException extends RuntimeException{
    public ResetPasswordException(String message) {
        super(message);
    }
}
