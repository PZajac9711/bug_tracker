package org.bugtracker.bugtracker.model.repository;

import org.bugtracker.bugtracker.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByLoginOrEmail(String login, String email);
    Optional<User> findUserByEmail(String email);
    User findByLogin(String login);
}
