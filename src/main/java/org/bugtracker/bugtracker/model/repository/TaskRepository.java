package org.bugtracker.bugtracker.model.repository;

import org.bugtracker.bugtracker.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByProjectName(String projectName);
}
