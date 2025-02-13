package com.demo.todo.controller;

import com.demo.todo.dto.TaskDTO;
import com.demo.todo.entity.Status;
import com.demo.todo.entity.Task;
import com.demo.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskDTO> getTasks(@RequestParam(value = "status", required = false) Status status) {
        return convertEntitiestoDTOList(taskService.getTasks(Optional.ofNullable(status)));
    }

    @GetMapping("/tasks/{taskId}")
    public TaskDTO createTask(@PathVariable UUID taskId) {
        Task task = taskService.getTaskByUUID(taskId);
        TaskDTO taskDTO = convertEntityToDTO(task);
        return taskDTO;
    }

    @PostMapping("/task")
    public String createTask(@RequestBody(required = true) TaskDTO taskDTO) {
        Task newTask = convertDTOtoEntity(taskDTO);
        taskService.createTask(newTask);
        return "created";
    }

    //These should be in a util file of some sort
    public static TaskDTO convertEntityToDTO(Task task) {
        return new TaskDTO(task);
    }

    public static List<TaskDTO> convertEntitiestoDTOList(List<Task> taskList){
        return taskList.stream().map(TaskController::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    //Look into MapStruct or ModelMapper
    private Task convertDTOtoEntity(TaskDTO task) {
        return new Task(task.getName(), task.getDescription());
    }
}