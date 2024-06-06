package com.subscriptionmanager.service;

import com.subscriptionmanager.dto.SubscriptionDTO;
import com.subscriptionmanager.entity.Subscription;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SubscriptionService {
    Subscription create(Jwt jwt,SubscriptionDTO subscriptionDTO);
    Subscription update(UUID subscriptionId, SubscriptionDTO subscriptionDTO);
    void delete(UUID subscriptionId);
    List<Subscription> getAll();
    List<Subscription> getByUserId(Jwt jwt);
    List<Subscription> getByCategoryId(UUID categoryId);
    Subscription getById(UUID subscriptionId);
    List<Map<String, Object>> getTotalAmountByServiceName();
}