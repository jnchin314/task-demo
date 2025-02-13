package com.demo.todo.repository;

import com.demo.todo.entity.Status;
import com.demo.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(Status status);
}
