package com.subscriptionmanager.repository;

import com.subscriptionmanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.isActive = true")
    List<Category> findByIsActiveTrue();

    @Query("SELECT c FROM Category c WHERE c.id = :id AND c.isActive = true")
    Optional<Category> findActiveById(Long id);
}
