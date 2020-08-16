package org.bugtracker.bugtracker.controllers;

import org.bugtracker.bugtracker.model.dto.TokenResponse;
import org.bugtracker.bugtracker.model.dto.UserAuthenticateRequest;
import org.bugtracker.bugtracker.model.dto.UserRegistrationRequest;
import org.bugtracker.bugtracker.model.services.RegistrationAndAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public")
public class RegistrationAndAuthenticationController {
    private RegistrationAndAuthenticationService registrationAndAuthenticationService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public RegistrationAndAuthenticationController(RegistrationAndAuthenticationService registrationAndAuthenticationService, AuthenticationManager authenticationManager) {
        this.registrationAndAuthenticationService = registrationAndAuthenticationService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<Void> registerUser(@Validated @RequestBody UserRegistrationRequest userRegistrationRequest) {
        registrationAndAuthenticationService.registerUser(userRegistrationRequest);
        return ResponseEntity.status(201).build();
    }
    @PostMapping(value = "/authenticate")
    public TokenResponse authenticateUser(@RequestBody UserAuthenticateRequest userAuthenticateRequest){
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userAuthenticateRequest.getLogin(), userAuthenticateRequest.getPassword());
        //ToDo: obsluzyc wyjatek InternalAuthenticationServiceException jak jest bledny login bo rzuca jak nie ma uzytkownika
        authenticationManager.authenticate(user);
        return registrationAndAuthenticationService.generateTokenForUser(user.getName());
    }
}
