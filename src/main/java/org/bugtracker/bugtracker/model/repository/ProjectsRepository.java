package org.bugtracker.bugtracker.model.repository;

import org.bugtracker.bugtracker.model.entities.Projects;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectsRepository extends CrudRepository<Projects,Long> {
    Optional<Projects> findByProjectName(String projectName);
}
