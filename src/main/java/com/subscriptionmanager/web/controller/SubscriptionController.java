package com.subscriptionmanager.web.controller;

import com.subscriptionmanager.domain.dto.SubscriptionDTO;
import com.subscriptionmanager.domain.entity.Subscription;
import com.subscriptionmanager.domain.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public List<Subscription> getAll() {
        return subscriptionService.getAll();
    }

    @GetMapping("/{id:[0-9a-fA-F\\-]+}")
    public ResponseEntity<Subscription> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(subscriptionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Subscription> create(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.create(jwt, subscriptionDTO));
    }

    @PutMapping("/{id:[0-9a-fA-F\\-]+}")
    public ResponseEntity<Subscription> update(@PathVariable UUID id,
                                               @Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.update(id, subscriptionDTO));
    }

    @DeleteMapping("/{id:[0-9a-fA-F\\-]+}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        subscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-amounts")
    public List<Map<String, Object>> getTotalAmountsByServiceName() {
        return subscriptionService.getTotalAmountByServiceName();
    }

    @GetMapping("/category/{id:[0-9a-fA-F\\-]+}")
    public List<Subscription> getCategoryById(@PathVariable UUID id) {
        return subscriptionService.getByCategoryId(id);
    }
}
