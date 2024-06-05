package com.subscriptionmanager.service.impl;

import com.subscriptionmanager.dto.SubscriptionDTO;
import com.subscriptionmanager.entity.Category;
import com.subscriptionmanager.entity.Subscription;
import com.subscriptionmanager.entity.User;
import com.subscriptionmanager.exception.EntityNotFoundException;
import com.subscriptionmanager.repository.CategoryRepository;
import com.subscriptionmanager.repository.SubscriptionRepository;
import com.subscriptionmanager.repository.UserRepository;
import com.subscriptionmanager.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultSubscriptionService implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Subscription create(SubscriptionDTO subscriptionDTO) {
        User user = userRepository.findActiveById(subscriptionDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User", "id", subscriptionDTO.getUserId()));

        Category category = categoryRepository.findActiveById(subscriptionDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category", "id", subscriptionDTO.getCategoryId()));

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setCategory(category);
        subscription.setServiceName(subscriptionDTO.getServiceName());
        subscription.setNextPaymentDate(subscriptionDTO.getNextPaymentDate());
        subscription.setAmount(subscriptionDTO.getAmount());
        subscription.setCurrency(subscriptionDTO.getCurrency());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription update(Long subscriptionId, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findActiveById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription", "id", subscriptionId));

        User user = userRepository.findActiveById(subscriptionDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User", "id", subscriptionDTO.getUserId()));

        Category category = categoryRepository.findActiveById(subscriptionDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category", "id", subscriptionDTO.getCategoryId()));

        subscription.setUser(user);
        subscription.setCategory(category);
        subscription.setServiceName(subscriptionDTO.getServiceName());
        subscription.setNextPaymentDate(subscriptionDTO.getNextPaymentDate());
        subscription.setAmount(subscriptionDTO.getAmount());
        subscription.setCurrency(subscriptionDTO.getCurrency());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void delete(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findActiveById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription", "id", subscriptionId));
        subscription.setActive(false);
        subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findActive();
    }

    @Override
    public List<Subscription> getByUserId(Long userId) {
        return subscriptionRepository.findActiveByUserId(userId);
    }

    @Override
    public List<Subscription> getByCategoryId(Long categoryId) {
        return subscriptionRepository.findActiveByCategoryId(categoryId);
    }

    @Override
    public Subscription getById(Long subscriptionId) {
        return subscriptionRepository.findActiveById(subscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("Subscription", "id", subscriptionId));
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