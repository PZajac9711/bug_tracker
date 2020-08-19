package org.bugtracker.bugtracker.model.services.imp;

import org.bugtracker.bugtracker.model.entities.User;
import org.bugtracker.bugtracker.model.exceptions.custom.ResetPasswordException;
import org.bugtracker.bugtracker.model.jwt.JwtRead;
import org.bugtracker.bugtracker.model.repository.UserRepository;
import org.bugtracker.bugtracker.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private JwtRead jwtRead;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(JwtRead jwtRead, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtRead = jwtRead;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void resetPassword(String token, String password) {
        //To Do handle moment where token expired \/ and generate exception
        //Signature exception
        String email = jwtRead.getEmailFromResetToken(token);
        Optional<User> user = userRepository.findUserByEmail(email);
        if(!user.isPresent()){
            throw new ResetPasswordException("There's no user with this email");
        }
        user.get().setPassword(passwordEncoder.encode(password));
        userRepository.save(user.get());
    }
}
