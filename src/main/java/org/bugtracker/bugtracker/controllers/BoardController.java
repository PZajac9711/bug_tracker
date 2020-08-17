package org.bugtracker.bugtracker.controllers;

import org.bugtracker.bugtracker.model.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private")
public class BoardController {
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/createProject")
    public ResponseEntity<Void> createProject(@RequestHeader(name = "authorization") String token, @RequestParam String boardName){
        boardService.createNewBoard(token,boardName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
