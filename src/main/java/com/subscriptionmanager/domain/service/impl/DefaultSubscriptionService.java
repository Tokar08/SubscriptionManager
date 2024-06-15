package com.subscriptionmanager.domain.service.impl;

import com.nimbusds.jwt.JWT;
import com.subscriptionmanager.domain.dto.SubscriptionDTO;
import com.subscriptionmanager.domain.entity.Category;
import com.subscriptionmanager.domain.entity.Subscription;
import com.subscriptionmanager.domain.mapper.SubscriptionMapper;
import com.subscriptionmanager.exception.handler.DataEntityNotFoundException;
import com.subscriptionmanager.domain.repository.CategoryRepository;
import com.subscriptionmanager.domain.repository.SubscriptionRepository;
import com.subscriptionmanager.domain.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultSubscriptionService implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CategoryRepository categoryRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public Subscription create(Jwt jwt, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);

        subscription.setUserId(UUID.fromString(jwt.getSubject()));
        Category category = categoryRepository.findById(subscriptionDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        subscription.setCategory(category);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription update(UUID subscriptionId, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findActiveById(subscriptionId)
                .orElseThrow(() -> new DataEntityNotFoundException("Subscription", "id", subscriptionId));

        Category category = categoryRepository.findById(subscriptionDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        subscription.setCategory(category);

        subscriptionMapper.updateFromDTO(subscriptionDTO, subscription);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public void delete(UUID subscriptionId) {
        Subscription subscription = subscriptionRepository.findActiveById(subscriptionId)
                .orElseThrow(() -> new DataEntityNotFoundException("Subscription", "id", subscriptionId));
        subscription.setActive(false);
        subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findActive();
    }

    @Override
    public List<Subscription> getAllSubscriptionsByUserId(Jwt jwt) {
        return subscriptionRepository.getAllSubscriptionsByUserId(UUID.fromString(jwt.getSubject()));
    }

    @Override
    public List<Subscription> getByCategoryId(UUID categoryId) {
        return subscriptionRepository.findActiveByCategoryId(categoryId);
    }

    @Override
    public Subscription getById(UUID subscriptionId) {
        return subscriptionRepository.findActiveById(subscriptionId)
                .orElseThrow(() -> new DataEntityNotFoundException("Subscription", "id", subscriptionId));
    }

    @Override
    public List<Map<String, Object>> getTotalAmountByServiceName() {
        List<Object[]> results = subscriptionRepository.findTotalAmountByServiceName();

        return results.stream()
                .map(result -> Map.of(
                        "label", result[0],
                        "value", result[1]
                ))
                .collect(Collectors.toList());
    }
}