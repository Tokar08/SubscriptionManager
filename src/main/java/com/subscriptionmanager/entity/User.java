package com.subscriptionmanager.entity;

import com.subscriptionmanager.validator.constraint.ValidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@ValidEntity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;


    @NotBlank
    @Size(min = 3, max = 255)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(updatable = false, name = "created_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updateAt = LocalDateTime.now();
}
