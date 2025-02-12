package com.demo.todo.dto;

import com.demo.todo.entity.Status;
import com.demo.todo.entity.Task;

public class TaskDTO {
    String name;
    String description;
    Status status;

    public TaskDTO() {
    }

    public TaskDTO(Task task){
        this.name = task.getName();
        this.status = task.getStatus();
        this.description = task.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
