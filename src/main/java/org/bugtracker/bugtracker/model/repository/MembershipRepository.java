package org.bugtracker.bugtracker.model.repository;

import org.bugtracker.bugtracker.model.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findAllByLogin(String login);
    Optional<Membership> findByLoginAndProjectName(String login, String projectName);
}
