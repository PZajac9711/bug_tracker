package org.bugtracker.bugtracker.model.dto;

import com.sun.istack.NotNull;

public class UserRegistrationRequest {
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String email;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
