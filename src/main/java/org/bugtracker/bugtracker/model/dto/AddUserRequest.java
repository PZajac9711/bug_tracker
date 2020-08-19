package org.bugtracker.bugtracker.model.dto;

public class AddUserRequest {
    private String userName;
    private String projectName;

    public AddUserRequest(String userName, String projectName) {
        this.userName = userName;
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
