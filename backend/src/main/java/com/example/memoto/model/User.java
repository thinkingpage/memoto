package com.example.memoto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @Column(unique=true)
    private String id;
    @Column(unique=true)
    private String username;
    @Column(unique=true)
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
}
