package com.demo.todo.dto;

import com.demo.todo.entity.Status;
import com.demo.todo.entity.Task;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"id"})
public class TaskDTO {
    UUID id;
    String name;
    String description;
    Status status;

    public TaskDTO() {
    }

    public TaskDTO(Task task){
        this.name = task.getName();
        this.status = task.getStatus();
        this.description = task.getDescription();
        this.id = task.getUuid();
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
