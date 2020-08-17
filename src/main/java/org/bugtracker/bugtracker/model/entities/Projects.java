package org.bugtracker.bugtracker.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "projectName")
    private String projectName;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "owner")
    private String owner;

    public Projects() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    private Projects(String projectName, LocalDateTime createdTime, String owner){
        this.projectName = projectName;
        this.createdTime = createdTime;
        this.owner = owner;
    }
    public static class Builder{
        private String projectName;
        private LocalDateTime createdTime;
        private String owner;

        public Builder setProjectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder setCreatedTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public Projects build(){
            return new Projects(projectName,createdTime,owner);
        }
    }
}
