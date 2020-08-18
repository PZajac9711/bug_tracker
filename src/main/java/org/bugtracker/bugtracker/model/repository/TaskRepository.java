package org.bugtracker.bugtracker.model.repository;

import org.bugtracker.bugtracker.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByProjectName(String projectName);
    Optional<Task> findByProjectNameAndTaskDescription(String projectName, String taskDescription);
}
