package com.subscriptionmanager.repository;

import com.subscriptionmanager.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    @Query("SELECT s FROM Subscription s WHERE s.isActive = true")
    List<Subscription> findActive();

    @Query("SELECT s FROM Subscription s WHERE s.subscriptionId = :subscriptionId AND s.isActive = true")
    Optional<Subscription> findActiveById(UUID subscriptionId);

    @Query("SELECT s FROM Subscription s WHERE s.userId = :userId AND s.isActive = true")
    List<Subscription> findActiveByUserId(UUID userId);

    @Query("SELECT s FROM Subscription s WHERE s.category.categoryId = :categoryId AND s.isActive = true")
    List<Subscription> findActiveByCategoryId(UUID categoryId);

    @Query("SELECT s.serviceName, SUM(s.amount) FROM Subscription s WHERE s.isActive = true GROUP BY s.serviceName")
    List<Object[]> findTotalAmountByServiceName();
}
