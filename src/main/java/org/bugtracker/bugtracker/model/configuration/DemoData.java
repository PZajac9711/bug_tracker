package org.bugtracker.bugtracker.model.configuration;

import org.bugtracker.bugtracker.model.entities.User;
import org.bugtracker.bugtracker.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoData {
    private UserRepository repo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DemoData(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        User user = new User.Builder()
                .setCreatedDate(java.time.LocalDateTime.now())
                .setPassword(passwordEncoder.encode("admin"))
                .setEmail("admin@admin.pl")
                .setLogin("admin")
                .build();
        repo.save(user);
    }
}
