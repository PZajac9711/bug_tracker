package org.bugtracker.bugtracker.model.services;

import org.bugtracker.bugtracker.model.dto.UserRegistrationRequest;

public interface RegistrationAndAuthenticationService {
    void registerUser(UserRegistrationRequest userRegistrationRequest);
}
