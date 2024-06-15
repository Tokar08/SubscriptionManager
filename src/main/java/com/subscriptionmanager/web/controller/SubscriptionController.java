package com.subscriptionmanager.web.controller;

import com.subscriptionmanager.web.dto.SubscriptionDTO;
import com.subscriptionmanager.domain.entity.Subscription;
import com.subscriptionmanager.domain.service.SubscriptionService;
import com.subscriptionmanager.domain.mapper.SubscriptionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final SubscriptionMapper subscriptionMapper;

    @GetMapping("/all")
    public List<Subscription> getAll() {
        return subscriptionService.getAll();
    }

    @GetMapping
    public List<Subscription> getAllSubscriptionsByUserId(@AuthenticationPrincipal Jwt jwt)
    {
        return subscriptionService.getAllSubscriptionsByUserId(jwt);
    }

    @GetMapping("/{id:[0-9a-fA-F\\-]+}")
    public ResponseEntity<Subscription> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(subscriptionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> create(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody SubscriptionDTO subscriptionDTO) {

        Subscription subscription = subscriptionService.create(jwt, subscriptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionMapper.toDTO(subscription));
    }

    @PutMapping("/{id:[0-9a-fA-F\\-]+}")
    public ResponseEntity<SubscriptionDTO> update(@PathVariable UUID id,
                                                  @Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionService.update(id, subscriptionDTO);
        return ResponseEntity.ok(subscriptionMapper.toDTO(subscription));
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
