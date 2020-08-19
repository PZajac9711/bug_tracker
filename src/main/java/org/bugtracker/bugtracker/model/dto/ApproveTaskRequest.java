package org.bugtracker.bugtracker.model.dto;

public class ApproveTaskRequest {
    private String projectName;
    private String taskName;
    private boolean approved;

    public ApproveTaskRequest(String projectName, String taskName, boolean approved) {
        this.projectName = projectName;
        this.taskName = taskName;
        this.approved = approved;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
