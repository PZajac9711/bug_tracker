package org.bugtracker.bugtracker.controllers;

import org.bugtracker.bugtracker.model.dto.CreateTaskRequest;
import org.bugtracker.bugtracker.model.dto.TasksForProjectResponse;
import org.bugtracker.bugtracker.model.entities.Membership;
import org.bugtracker.bugtracker.model.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(value = "/findAllBoards")
    public List<Membership> findAllBoard(@RequestHeader(name = "authorization") String token){
        return boardService.getAllBoards(token);
    }
    @PostMapping(value = "/addTask")
    public ResponseEntity<Void> addTask(@RequestBody CreateTaskRequest createTaskRequest, @RequestHeader(name = "authorization") String token){
        this.boardService.createNewTask(token,createTaskRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/getTasks")
    public TasksForProjectResponse getTasks(@RequestParam String boardName, @RequestHeader(name = "authorization") String token){
        return boardService.getTasks(token,boardName);
    }
}
