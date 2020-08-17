package org.bugtracker.bugtracker.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_description")
    private String taskDescription;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "assign_to")
    private String assignTo;
    private boolean done;
    private boolean approved;

    public Task() {
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    private Task(String taskDescription, String projectName){
        this.taskDescription = taskDescription;
        this.projectName = projectName;
    }

    public static class Builder{
        private String taskDescription;
        private String projectName;

        public Builder setTaskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
            return this;
        }

        public Builder setProjectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Task build(){
            return new Task(taskDescription,projectName);
        }
    }
}
