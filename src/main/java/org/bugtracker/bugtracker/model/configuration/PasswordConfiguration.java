package org.bugtracker.bugtracker.model.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfiguration {
    private final int PASSWORD_STRENGTH = 10;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(PASSWORD_STRENGTH);
    }

}
