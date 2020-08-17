package org.bugtracker.bugtracker.model.services;

import org.bugtracker.bugtracker.model.dto.CreateTaskRequest;
import org.bugtracker.bugtracker.model.dto.TasksForProjectResponse;
import org.bugtracker.bugtracker.model.entities.Membership;

import java.util.List;

public interface BoardService {
    void createNewBoard(String token, String boardName);
    List<Membership> getAllBoards(String token);
    void createNewTask(String token, CreateTaskRequest createTaskRequest);
    TasksForProjectResponse getTasks(String token, String projectName);
}
