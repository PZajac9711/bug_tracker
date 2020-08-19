package org.bugtracker.bugtracker.model.exceptions.custom;

public class TaskException extends RuntimeException{
    public TaskException(String message) {
        super(message);
    }
}
