package org.bugtracker.bugtracker.model.repository;

import org.bugtracker.bugtracker.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByLoginOrEmail(String login, String email);
}
