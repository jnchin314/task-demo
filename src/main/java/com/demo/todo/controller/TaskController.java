package com.demo.todo.controller;

import com.demo.todo.dto.TaskDTO;
import com.demo.todo.entity.Status;
import com.demo.todo.entity.Task;
import com.demo.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskDTO> getTasks(@RequestParam(value = "status", required = false) Status status) {

        return taskService.getTasks(Optional.ofNullable(status))
                .stream().map(TaskController::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/task")
    public String createTask(@RequestBody TaskDTO taskDTO) {
        Task newTask = convertDTOtoEntity(taskDTO);
        taskService.createTask(newTask);
        return "created";
    }

    private static TaskDTO convertEntityToDTO(Task task) {
        return new TaskDTO(task);
    }

    //Look into MapStruct or ModelMapper
    private Task convertDTOtoEntity(TaskDTO task) {
        return new Task(task.getName(), task.getDescription());
    }
}