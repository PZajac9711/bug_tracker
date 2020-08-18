package org.bugtracker.bugtracker.controllers;

import org.bugtracker.bugtracker.model.dto.CreateTaskRequest;
import org.bugtracker.bugtracker.model.dto.SignToMeRequest;
import org.bugtracker.bugtracker.model.dto.TasksForProjectResponse;
import org.bugtracker.bugtracker.model.dto.UpdateTaskDetailsRequest;
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
    @PostMapping(value = "/updateTaskDetails")
    public ResponseEntity<Void> updateDetails(@RequestBody UpdateTaskDetailsRequest updateTaskDetailsRequest){
        this.boardService.updateTaskDetails(updateTaskDetailsRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/signToMe")
    public ResponseEntity<Void> signTaskToMe(@RequestBody SignToMeRequest signToMeRequest,
                                             @RequestHeader(name = "authorization") String token){
        this.boardService.assignTaskToMe(signToMeRequest,token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //ToDo: trzeba zmienic objekt jaki przyjmujemy, imo to te same wartosci ale lepiej bedzie zrobic extenda na nowa klase
    @PostMapping(value = "/markAsDone")
    public ResponseEntity<Void> markAsDone(@RequestBody SignToMeRequest signToMeRequest,
                                             @RequestHeader(name = "authorization") String token){
        this.boardService.markAsDone(signToMeRequest, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
