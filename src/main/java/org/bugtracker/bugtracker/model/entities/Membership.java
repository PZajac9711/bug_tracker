package org.bugtracker.bugtracker.model.entities;

import javax.persistence.*;

@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    @Column(name = "project_name")
    private String projectName;
    private String role;

    public Membership() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    private Membership(String login, String name, String role){
        this.login = login;
        this.projectName = name;
        this.role = role;
    }
    public static class Builder{
        private String login;
        private String name;
        private String role;

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }
        public Membership build(){
            return new Membership(login,name,role);
        }
    }
}
