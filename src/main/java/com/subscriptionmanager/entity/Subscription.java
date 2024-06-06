package com.subscriptionmanager.entity;

import com.subscriptionmanager.validator.constraint.ValidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "subscriptions")
@ValidEntity
public class Subscription {
    @Id
    @Column(name = "subscription_id")
    private UUID subscriptionId = UUID.randomUUID();

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "next_payment_date", nullable = false)
    @FutureOrPresent(message = "Next payment date must be present or in the future")
    private LocalDateTime nextPaymentDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
