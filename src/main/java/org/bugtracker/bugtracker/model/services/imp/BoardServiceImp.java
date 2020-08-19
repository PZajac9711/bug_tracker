package org.bugtracker.bugtracker.model.services.imp;

import org.bugtracker.bugtracker.model.dto.*;
import org.bugtracker.bugtracker.model.entities.Membership;
import org.bugtracker.bugtracker.model.entities.Projects;
import org.bugtracker.bugtracker.model.entities.Task;
import org.bugtracker.bugtracker.model.exceptions.custom.BoardAlreadyExistException;
import org.bugtracker.bugtracker.model.exceptions.custom.TaskException;
import org.bugtracker.bugtracker.model.jwt.JwtRead;
import org.bugtracker.bugtracker.model.repository.MembershipRepository;
import org.bugtracker.bugtracker.model.repository.ProjectsRepository;
import org.bugtracker.bugtracker.model.repository.TaskRepository;
import org.bugtracker.bugtracker.model.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
        String userName = jwtRead.getLogin(token);
        Optional<Projects> project = projectsRepository.findByProjectName(createTaskRequest.getBoardName());
        if (!project.isPresent() || !project.get().getOwner().equals(userName.toLowerCase())) {
            throw new TaskException("You can't create task");
        }
        if (taskRepository.findByProjectNameAndTaskDescription(
                createTaskRequest.getBoardName(),
                createTaskRequest.getDescription()).isPresent()){
            throw new TaskException("Task already exist");
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
                .filter(item -> item.getAssignTo().length() >= 3)
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
        return new TasksForProjectResponse(toDo, inProgress, checkMe, approved);
    }

    @Override
    public void updateTaskDetails(UpdateTaskDetailsRequest updateTaskDetailsRequest) {
        //ToDo: sprawdzic czy task i user naleza do tej tablicy
        //ToDo: wyjatek jak nie bedzie takiego taska jakims cudem
        Optional<Task> task = taskRepository.findByProjectNameAndTaskDescription(updateTaskDetailsRequest.getProjectName(), updateTaskDetailsRequest.getTaskName());
        if (!task.isPresent()) {
            throw new TaskException("There's no task");
        }
        task.get().setTaskDetails(updateTaskDetailsRequest.getDetails());
        this.taskRepository.save(task.get());
    }

    @Override
    public void assignTaskToMe(SignToMeRequest signToMeRequest, String token) {
        //ToDo: sprawdzic czy task i user naleza do tej tablicy
        //ToDo: wyjatek jak nie bedzie takiego taska jakims cudem
        Optional<Task> task = taskRepository.findByProjectNameAndTaskDescription(signToMeRequest.getProjectName(), signToMeRequest.getTaskName());
        if (!task.isPresent()) {
            System.out.println("empty!");
            //exception
        }
        task.get().setAssignTo(jwtRead.getLogin(token));
        taskRepository.save(task.get());
    }

    @Override
    public void markAsDone(SignToMeRequest signToMeRequest, String token) {
        Optional<Task> task = taskRepository.findByProjectNameAndTaskDescription(signToMeRequest.getProjectName(), signToMeRequest.getTaskName());
        if (!task.isPresent()) {
            System.out.println("empty!");
            //exception
        }
        if(!task.get().getAssignTo().equals(jwtRead.getLogin(token))){
            throw new TaskException("You can't mark it");
        }
        task.get().setDone(true);
        this.taskRepository.save(task.get());
    }

    @Override
    public void approveTask(ApproveTaskRequest approveTaskRequest, String token) {
        Optional<Task> task = taskRepository.findByProjectNameAndTaskDescription(
                approveTaskRequest.getProjectName(),
                approveTaskRequest.getTaskName()
        );
        Optional<Projects> project = projectsRepository.findByProjectName(approveTaskRequest.getProjectName());
        if (!project.isPresent() || !project.get().getOwner().equals(jwtRead.getLogin(token))) {
            throw new TaskException("You can't approve or reject tasks");
        }
        if (approveTaskRequest.isApproved()) {
            task.get().setApproved(true);
        } else {
            task.get().setDone(false);
        }
        this.taskRepository.save(task.get());
    }

    @Override
    public void assignTaskTo(SignToMeRequest assignTo, String token, String toUserName) {
        //ToDo: sprawdzic czy task i user naleza do tej tablicy
        Optional<Task> task = taskRepository.findByProjectNameAndTaskDescription(assignTo.getProjectName(), assignTo.getTaskName());
        Optional<Projects> project = projectsRepository.findByProjectName(assignTo.getProjectName());
        if (!task.isPresent() || !project.get().getOwner().equals(jwtRead.getLogin(token))) {
            throw new TaskException("You can't assign tasks");
        }
        task.get().setAssignTo(toUserName);
        taskRepository.save(task.get());
    }

    @Override
    public void addUserToProject(AddUserRequest addUserRequest, String token) {
        //ToDo: sprawdzic czy taka tablica istnieje
        //ToDo: sprawdzic czy osoba ktora przypisujemy nie jest juz czasem w tym projekcie
        Optional<Projects> project = projectsRepository.findByProjectName(addUserRequest.getProjectName());
        if(!project.isPresent() || !project.get().getOwner().equals(jwtRead.getLogin(token))){
            throw new TaskException("You can't add people");
        }
        if(membershipRepository.findByLoginAndProjectName(addUserRequest.getUserName().toLowerCase(),addUserRequest.getProjectName()).isPresent()){
            throw new TaskException("User already is a member");
        }
        Membership membership = new Membership.Builder()
                .setLogin(addUserRequest.getUserName())
                .setName(addUserRequest.getProjectName())
                .setRole("Not implemented yet")
                .build();
        try{
            this.membershipRepository.save(membership);
        }
        catch (DataIntegrityViolationException e){
            throw new TaskException("User not exists");
        }
    }

    //add user when project created
    private void addUserToBoard(String userName, String boardName) {
        this.membershipRepository.save(new Membership.Builder()
                .setLogin(userName)
                .setName(boardName)
                .setRole("Not implemented yet")
                .build());
    }
}
