package org.bugtracker.bugtracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
public class BoardController {
    @GetMapping(value = "/getProjects")
    public String test(){
        return "Hello?";
    }
}
