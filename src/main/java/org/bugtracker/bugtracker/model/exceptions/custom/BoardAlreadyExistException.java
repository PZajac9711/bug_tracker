package org.bugtracker.bugtracker.model.exceptions.custom;

public class BoardAlreadyExistException extends RuntimeException{
    public BoardAlreadyExistException(String message) {
        super(message);
    }
}
