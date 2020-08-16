package org.bugtracker.bugtracker.model.exceptions.custom;

public class UserRegistrationException extends RuntimeException{
    public UserRegistrationException(String message) {
        super(message);
    }
}
