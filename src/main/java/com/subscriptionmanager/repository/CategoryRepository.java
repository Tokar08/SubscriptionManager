package com.subscriptionmanager.repository;

import com.subscriptionmanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c WHERE c.isActive = true")
    List<Category> findByIsActiveTrue();

    @Query("SELECT c FROM Category c WHERE c.categoryId = :id AND c.isActive = true")
    Optional<Category> findActiveById(@Param("id") UUID id);
}
