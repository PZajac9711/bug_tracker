package org.bugtracker.bugtracker.model.services.imp;

import org.bugtracker.bugtracker.model.dto.UserRegistrationRequest;
import org.bugtracker.bugtracker.model.entities.User;
import org.bugtracker.bugtracker.model.exceptions.custom.UserRegistrationException;
import org.bugtracker.bugtracker.model.repository.UserRepository;
import org.bugtracker.bugtracker.model.services.RegistrationAndAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class RegistrationAndAuthenticationServiceImp implements RegistrationAndAuthenticationService {
    private UserRepository userRepository;

    @Autowired
    public RegistrationAndAuthenticationServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserRegistrationRequest userRegistrationRequest) {
        //userList represent users from db, it need to be a list because there's scenario
        //when one user can have the same login and another user can have the same email
        List<User> userList = userRepository.findUserByLoginOrEmail(userRegistrationRequest.getLogin().toLowerCase(),userRegistrationRequest.getEmail().toLowerCase());
        if(userList.size() > 0){
            if(userList.size() == 2){
                throw new UserRegistrationException("login and email are taken");
            }
            if(userList.get(0).getLogin().equals(userRegistrationRequest.getLogin().toLowerCase())){
                if(userList.get(0).getEmail().equals(userRegistrationRequest.getEmail().toLowerCase())){
                    throw new UserRegistrationException("login and email are taken");
                }
                throw new UserRegistrationException("login is taken");
            }
            throw new UserRegistrationException("email is taken");
        }
        if(checkAllPatterns(userRegistrationRequest)){
            User user = new User.Builder()
                    .setLogin(userRegistrationRequest.getLogin().toLowerCase())
                    .setEmail(userRegistrationRequest.getEmail().toLowerCase())
                    .setPassword(userRegistrationRequest.getPassword())
                    .setCreatedDate(java.time.LocalDateTime.now())
                    .build();
            userRepository.save(user);
        }
    }

    private boolean checkAllPatterns(UserRegistrationRequest userRegistrationRequest) {
        return checkEmailPattern(userRegistrationRequest.getEmail())
                && checkLoginPattern(userRegistrationRequest.getLogin())
                && checkPasswordPattern(userRegistrationRequest.getPassword());
    }

    private boolean checkLoginPattern(String login) {
        String regex = "^[a-zA-Z0-9]{3,}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(login).matches()) {
            throw new UserRegistrationException("Wrong login");
        }
        return true;
    }

    private boolean checkPasswordPattern(String password) {
        return true;
    }

    private boolean checkEmailPattern(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(email).matches()) {
            throw new UserRegistrationException("Wrong email");
        }
        return true;
    }
}
