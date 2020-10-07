package org.bugtracker.bugtracker.controllers;

import org.bugtracker.bugtracker.model.dto.ResetPasswordRequest;
import org.bugtracker.bugtracker.model.repository.UserRepository;
import org.bugtracker.bugtracker.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        userService.resetPassword(resetPasswordRequest.getToken(),resetPasswordRequest.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/start")
    public ResponseEntity<Void> notice(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
