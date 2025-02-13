package com.demo.todo.controller;

import com.demo.todo.entity.Status;
import com.demo.todo.entity.Task;
import com.demo.todo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.demo.todo.controller.TaskController.convertEntitiestoDTOList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@ExtendWith({ SpringExtension.class})
@WebMvcTest
class TaskControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    TaskService taskService;

    @Test
    void givenNoStatus_whenGettingAllTasks_thenReturnTaskList() throws Exception {
        ArrayList<Task> taskList = new ArrayList<>();

        Task task = new Task("name", "desc");
        UUID uuid = UUID.randomUUID();
        task.setUuid(uuid);

        Task task2 = new Task("name", "desc");
        UUID uuid2 = UUID.randomUUID();
        task2.setUuid(uuid2);

        taskList.add(task);
        taskList.add(task2);

        String jsonTasks = objectMapper.writeValueAsString(convertEntitiestoDTOList(taskList));

        when(taskService.getTasks(Optional.ofNullable(null))).thenReturn(taskList);

        mockMvc.perform(
                get("/tasks").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonTasks));
    }

}