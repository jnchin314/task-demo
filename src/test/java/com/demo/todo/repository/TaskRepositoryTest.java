package com.demo.todo.repository;

import com.demo.todo.entity.Task;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestEntityManager
public class TaskRepositoryTest {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TestEntityManager entityManager;



    @Test
    public void whenFindById_thenReturnTask() {
        String name = "task name";
        String description = "description";
        Task task = new Task(name, description);
        entityManager.persistAndFlush(task);


        Optional<Task> foundTask = taskRepository.findById(task.getId());

        assertThat(foundTask).isPresent();
        assertThat(foundTask.get().getName()).isEqualTo(name);
    }

    @Test
    @Transactional
    public void givenNameUpdate_whenSave_thenReturnUpdatedTask() {
        String name = "task name";
        String someOtherName = "some other name";
        String description = "description";


        Task task = new Task(name, description);
        entityManager.persistAndFlush(task);

        Optional<Task> foundTask = taskRepository.findById(task.getId());
        assertThat(foundTask).isPresent();

        foundTask.get().setName(someOtherName);
        taskRepository.saveAndFlush(foundTask.get());


        Task modifiedTask = entityManager.find(Task.class, 1L);
        assertEquals(someOtherName, modifiedTask.getName());
    }
}
