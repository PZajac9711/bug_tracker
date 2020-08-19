package org.bugtracker.bugtracker.controllers;

import org.bugtracker.bugtracker.model.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value = "/api/public")
public class MailController {
    private MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping(value = "/generateResetPasswordMail")
    public ResponseEntity<Void> generateResetPasswordMail(@RequestParam String email) throws MessagingException {
        mailService.generateResetPasswordMail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
