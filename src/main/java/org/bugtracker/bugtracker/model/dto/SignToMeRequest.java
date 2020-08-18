package org.bugtracker.bugtracker.model.dto;

public class SignToMeRequest {
    private String projectName;
    private String taskName;

    public SignToMeRequest(String projectName, String taskName) {
        this.projectName = projectName;
        this.taskName = taskName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
