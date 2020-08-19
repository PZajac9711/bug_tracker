package org.bugtracker.bugtracker.model.services;

import org.bugtracker.bugtracker.model.dto.*;
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
    void approveTask(ApproveTaskRequest approveTaskRequest, String token);
    void assignTaskTo(SignToMeRequest assignTo, String token, String toUserName);
    void addUserToProject(AddUserRequest addUserRequest, String token);
}
