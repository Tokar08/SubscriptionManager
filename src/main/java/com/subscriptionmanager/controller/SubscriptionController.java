package com.subscriptionmanager.controller;

import com.subscriptionmanager.dto.SubscriptionDTO;
import com.subscriptionmanager.entity.Subscription;
import com.subscriptionmanager.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public List<Subscription> getAll() {
        return subscriptionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(subscriptionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Subscription> create(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.create(jwt, subscriptionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> update(@PathVariable UUID id, @RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.update(id, subscriptionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        subscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-amounts")
    public List<Map<String, Object>> getTotalAmountsByServiceName() {
        return subscriptionService.getTotalAmountByServiceName();
    }
}
