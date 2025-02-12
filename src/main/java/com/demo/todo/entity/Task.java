package com.demo.todo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "task", schema = "todo",indexes = {
        @Index(name = "idx_uuid", columnList = "uuid")})
public class Task {

    public Task() {
        this.uuid = UUID.randomUUID();
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = Status.CREATED;
        this.uuid = UUID.randomUUID();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @Column(length = 50)
    String name;

    @Column(length = 250)
    String description;

    //not ideal this is Hibernate specific
    @CreationTimestamp
    private LocalDateTime createdDateTime;

    //not ideal this is Hibernate specific
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;

}
