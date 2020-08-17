package org.bugtracker.bugtracker.model.services.imp;

import org.bugtracker.bugtracker.model.entities.Projects;
import org.bugtracker.bugtracker.model.exceptions.custom.BoardAlreadyExistException;
import org.bugtracker.bugtracker.model.jwt.JwtRead;
import org.bugtracker.bugtracker.model.repository.ProjectsRepository;
import org.bugtracker.bugtracker.model.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardServiceImp implements BoardService {
    private ProjectsRepository projectsRepository;
    private JwtRead jwtRead;

    @Autowired
    public BoardServiceImp(ProjectsRepository projectsRepository, JwtRead jwtRead) {
        this.projectsRepository = projectsRepository;
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
    }
}
