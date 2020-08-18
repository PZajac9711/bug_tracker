package org.bugtracker.bugtracker.model.services.imp;

import org.bugtracker.bugtracker.model.dto.CreateTaskRequest;
import org.bugtracker.bugtracker.model.dto.SignToMeRequest;
import org.bugtracker.bugtracker.model.dto.TasksForProjectResponse;
import org.bugtracker.bugtracker.model.dto.UpdateTaskDetailsRequest;
import org.bugtracker.bugtracker.model.entities.Membership;
import org.bugtracker.bugtracker.model.entities.Projects;
import org.bugtracker.bugtracker.model.entities.Task;
import org.bugtracker.bugtracker.model.exceptions.custom.BoardAlreadyExistException;
import org.bugtracker.bugtracker.model.jwt.JwtRead;
import org.bugtracker.bugtracker.model.repository.MembershipRepository;
import org.bugtracker.bugtracker.model.repository.ProjectsRepository;
import org.bugtracker.bugtracker.model.repository.TaskRepository;
import org.bugtracker.bugtracker.model.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceImp implements BoardService {
    private ProjectsRepository projectsRepository;
    private MembershipRepository membershipRepository;
    private TaskRepository taskRepository;
    private JwtRead jwtRead;

    @Autowired
    public BoardServiceImp(ProjectsRepository projectsRepository, MembershipRepository membershipRepository, TaskRepository taskRepository, JwtRead jwtRead) {
        this.projectsRepository = projectsRepository;
        this.membershipRepository = membershipRepository;
        this.taskRepository = taskRepository;
        this.jwtRead = jwtRead;
    }

    @Override
    public void createNewBoard(String token, String boardName) {
        Optional<Projects> projects = projectsRepository.findByProjectName(boardName.toLowerCase());
        if (projects.isPresent()) {
            throw new BoardAlreadyExistException("board already exist");
        }
        String userName = jwtRead.getLogin(token);
        projectsRepository.save(new Projects.Builder()
                .setOwner(userName)
                .setProjectName(boardName)
                .setCreatedTime(java.time.LocalDateTime.now())
                .build());
        addUserToBoard(userName, boardName);
    }

    @Override
    public List<Membership> getAllBoards(String token) {
        String userName = jwtRead.getLogin(token);
        return membershipRepository.findAllByLogin(userName);
    }

    @Override
    public void createNewTask(String token, CreateTaskRequest createTaskRequest) {
        //ToDo: zrobic by taski nie mogly sie powtarzac w swoich projektach bo po co miec dwa takie same taski ?
        String userName = jwtRead.getLogin(token);
        Optional<Projects> project = projectsRepository.findByProjectName(createTaskRequest.getBoardName());
        if (!project.isPresent() || project.get().getOwner().equals(userName.toLowerCase())) {
            //throw exception
        }
        taskRepository.save(new Task.Builder()
                .setProjectName(createTaskRequest.getBoardName())
                .setTaskDescription(createTaskRequest.getDescription())
                .build());
    }

    @Override
    public TasksForProjectResponse getTasks(String token, String projectName) {
        //ToDo: sprawdzic czy username z tokena rzeczywiscie nalezy do tej tablicy
        //ToDo: rozwiazac jakos lepiej to przypisywanie do list
        List<Task> taskList = taskRepository.findAllByProjectName(projectName);
        List<Task> toDo = taskList.stream()
                .filter(item -> item.getAssignTo() == null)
                .collect(Collectors.toList());
        List<Task> inProgress = taskList.stream()
                .filter(item -> item.getAssignTo() != null)
                .filter(item -> item.getAssignTo().length() > 3)
                .filter(item -> !item.isDone())
                .collect(Collectors.toList());
        List<Task> checkMe = taskList.stream()
                .filter(item -> item.getAssignTo() != null)
                .filter(Task::isDone)
                .filter(item -> !item.isApproved())
                .collect(Collectors.toList());
        List<Task> approved = taskList.stream()
                .filter(item -> item.getAssignTo() != null)
                .filter(Task::isApproved)
                .collect(Collectors.toList());
        return new TasksForProjectResponse(toDo,inProgress,checkMe,approved);
    }

    @Override
    public void updateTaskDetails(UpdateTaskDetailsRequest updateTaskDetailsRequest) {
        //ToDo: sprawdzic czy task i user naleza do tej tablicy
        //ToDo: wyjatek jak nie bedzie takiego taska jakims cudem
        Optional<Task> task = taskRepository.findByProjectNameAndTaskDescription(updateTaskDetailsRequest.getProjectName(),updateTaskDetailsRequest.getTaskName());
        if(!task.isPresent()){
            System.out.println("empty!");
            //exception
        }
        task.get().setTaskDetails(updateTaskDetailsRequest.getDetails());
        this.taskRepository.save(task.get());
    }

    @Override
    public void assignTaskToMe(SignToMeRequest signToMeRequest, String token) {
        //ToDo: sprawdzic czy task i user naleza do tej tablicy
        //ToDo: wyjatek jak nie bedzie takiego taska jakims cudem
        Optional<Task> task = taskRepository.findByProjectNameAndTaskDescription(signToMeRequest.getProjectName(), signToMeRequest.getTaskName());
        if(!task.isPresent()){
            System.out.println("empty!");
            //exception
        }
        task.get().setAssignTo(jwtRead.getLogin(token));
        taskRepository.save(task.get());
    }

    @Override
    public void markAsDone(SignToMeRequest signToMeRequest, String token) {
        //ToDo: sprawdzic czy rzeczywiscie ten co martkuje jako done jest osoba ktora wykonuje task
        Optional<Task> task = taskRepository.findByProjectNameAndTaskDescription(signToMeRequest.getProjectName(), signToMeRequest.getTaskName());
        if(!task.isPresent()){
            System.out.println("empty!");
            //exception
        }
        task.get().setDone(true);
        this.taskRepository.save(task.get());
    }

    private void addUserToBoard(String userName, String boardName) {
        this.membershipRepository.save(new Membership.Builder()
                .setLogin(userName)
                .setName(boardName)
                .setRole("Not implemented yet")
                .build());
    }
}
