package org.bugtracker.bugtracker.controllers;

import org.bugtracker.bugtracker.model.dto.UserRegistrationRequest;
import org.bugtracker.bugtracker.model.services.RegistrationAndAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public")
@CrossOrigin
public class RegistrationAndAuthenticationController {
    private RegistrationAndAuthenticationService registrationAndAuthenticationService;

    @Autowired
    public RegistrationAndAuthenticationController(RegistrationAndAuthenticationService registrationAndAuthenticationService) {
        this.registrationAndAuthenticationService = registrationAndAuthenticationService;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<Void> registerUser(@Validated @RequestBody UserRegistrationRequest userRegistrationRequest) {
        registrationAndAuthenticationService.registerUser(userRegistrationRequest);
        return ResponseEntity.status(201).build();
    }
}
