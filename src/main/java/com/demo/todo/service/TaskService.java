package com.demo.todo.service;

import com.demo.todo.entity.Status;
import com.demo.todo.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.demo.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service

public class TaskService {
    @Autowired
    @Qualifier("taskRepository")
    TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getTasks(Optional<Status> status) {
        if(status.isEmpty()) {
            return taskRepository.findAll();
        }else{
            return taskRepository.findByStatus(status.get());
        }
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setName(updatedTask.getName());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            return taskRepository.save(task);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    } 

}
