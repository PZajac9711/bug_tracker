package org.bugtracker.bugtracker.model.services;

import org.bugtracker.bugtracker.model.dto.TokenResponse;
import org.bugtracker.bugtracker.model.dto.UserAuthenticateRequest;
import org.bugtracker.bugtracker.model.dto.UserRegistrationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RegistrationAndAuthenticationService extends UserDetailsService {
    void registerUser(UserRegistrationRequest userRegistrationRequest);
    TokenResponse generateTokenForUser(String login);
}
