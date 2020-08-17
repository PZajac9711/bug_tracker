package org.bugtracker.bugtracker.model.repository;

import org.bugtracker.bugtracker.model.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findAllByLogin(String login);
}
