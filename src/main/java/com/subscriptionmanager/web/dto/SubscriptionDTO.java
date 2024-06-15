package com.subscriptionmanager.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubscriptionDTO {

    @NotNull(message = "Category id is required")
    private UUID categoryId;

    @NotBlank(message = "Service name is required")
    private String serviceName;

    @NotNull(message = "Next payment date is required")
    private LocalDateTime nextPaymentDate;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Currency is required")
    private String currency;
}
