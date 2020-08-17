package org.bugtracker.bugtracker.model.dto;

public class CreateTaskRequest {
    private String boardName;
    private String description;

    public CreateTaskRequest(String boardName, String description) {
        this.boardName = boardName;
        this.description = description;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
