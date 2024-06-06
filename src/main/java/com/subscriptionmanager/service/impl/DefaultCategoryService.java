package com.subscriptionmanager.service.impl;

import com.subscriptionmanager.dto.CategoryDTO;
import com.subscriptionmanager.entity.Category;
import com.subscriptionmanager.exception.EntityNotFoundException;
import com.subscriptionmanager.repository.CategoryRepository;
import com.subscriptionmanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Jwt jwt, CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setUserId(UUID.fromString(jwt.getSubject()));
        category.setCategoryName(categoryDTO.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public Category update(UUID categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findActiveById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category", "id", categoryId));

        category.setCategoryName(categoryDTO.getCategoryName());

        return categoryRepository.save(category);
    }

    @Override
    public void delete(UUID categoryId) {
        Category category = categoryRepository.findActiveById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category", "id", categoryId));
        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findByIsActiveTrue();
    }

    @Override
    public List<Category> getByUserId(Jwt jwt) {
        return categoryRepository.findAll().stream()
                .filter(category -> category.isActive())
                .collect(Collectors.toList());
    }


    @Override
    public Category getById(UUID categoryId) {
        return categoryRepository.findActiveById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category", "id", categoryId));
    }
}