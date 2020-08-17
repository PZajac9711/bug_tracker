package org.bugtracker.bugtracker.model.exceptions.custom;

public class NoUserException extends RuntimeException{
    public NoUserException(String message) {
        super(message);
    }
}
