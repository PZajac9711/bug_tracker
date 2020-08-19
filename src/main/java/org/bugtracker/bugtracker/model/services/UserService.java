package org.bugtracker.bugtracker.model.services;

public interface UserService {
    void resetPassword(String token, String password);
}
