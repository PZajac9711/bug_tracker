package org.bugtracker.bugtracker.model.dto;

public class UpdateTaskDetailsRequest {
    private String projectName;
    private String taskName;
    private String details;

    public UpdateTaskDetailsRequest(String projectName, String taskName, String details) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    @Override
    public String toString() {
        return "UpdateTaskDetailsRequest{" +
                "projectName='" + projectName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
