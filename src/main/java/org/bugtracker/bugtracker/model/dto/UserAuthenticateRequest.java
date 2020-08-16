package org.bugtracker.bugtracker.model.dto;

public class UserAuthenticateRequest {
    private String login;
    private String password;

    public UserAuthenticateRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserAuthenticateRequest() {
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
}
