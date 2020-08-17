package org.bugtracker.bugtracker.model.dto;

import org.bugtracker.bugtracker.model.entities.Task;

import java.util.List;

public class TasksForProjectResponse {
    private List<Task> toDo;
    private List<Task> inProgress;
    private List<Task> checkMe;
    private List<Task> done;

    public TasksForProjectResponse(List<Task> toDo, List<Task> inProgress, List<Task> checkMe, List<Task> done) {
        this.toDo = toDo;
        this.inProgress = inProgress;
        this.checkMe = checkMe;
        this.done = done;
    }

    public List<Task> getToDo() {
        return toDo;
    }

    public void setToDo(List<Task> toDo) {
        this.toDo = toDo;
    }

    public List<Task> getInProgress() {
        return inProgress;
    }

    public void setInProgress(List<Task> inProgress) {
        this.inProgress = inProgress;
    }

    public List<Task> getCheckMe() {
        return checkMe;
    }

    public void setCheckMe(List<Task> checkMe) {
        this.checkMe = checkMe;
    }

    public List<Task> getDone() {
        return done;
    }

    public void setDone(List<Task> done) {
        this.done = done;
    }
}
