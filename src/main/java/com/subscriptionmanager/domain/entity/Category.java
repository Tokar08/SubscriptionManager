package com.subscriptionmanager.domain.entity;

import com.subscriptionmanager.validator.constraint.ValidEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "categories")
@ValidEntity
public class Category {

    @Id
    @Column(name = "category_id")
    private UUID categoryId = UUID.randomUUID();

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "is_active")
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
