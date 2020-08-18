package org.bugtracker.bugtracker.model.services;

import org.bugtracker.bugtracker.model.dto.CreateTaskRequest;
import org.bugtracker.bugtracker.model.dto.SignToMeRequest;
import org.bugtracker.bugtracker.model.dto.TasksForProjectResponse;
import org.bugtracker.bugtracker.model.dto.UpdateTaskDetailsRequest;
import org.bugtracker.bugtracker.model.entities.Membership;

import java.util.List;

public interface BoardService {
    void createNewBoard(String token, String boardName);
    List<Membership> getAllBoards(String token);
    void createNewTask(String token, CreateTaskRequest createTaskRequest);
    TasksForProjectResponse getTasks(String token, String projectName);
    void updateTaskDetails(UpdateTaskDetailsRequest updateTaskDetailsRequest);
    void assignTaskToMe(SignToMeRequest signToMeRequest, String token);
    void markAsDone(SignToMeRequest signToMeRequest, String token);
}
