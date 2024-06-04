package com.subscriptionmanager.service;

import com.subscriptionmanager.dto.SubscriptionDTO;
import com.subscriptionmanager.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription create(SubscriptionDTO subscriptionDTO);
    Subscription update(Long subscriptionId, SubscriptionDTO subscriptionDTO);
    void delete(Long subscriptionId);
    List<Subscription> getAll();
    List<Subscription> getByUserId(Long userId);
    List<Subscription> getByCategoryId(Long categoryId);
    Subscription getById(Long subscriptionId);
}