package com.subscriptionmanager.service.impl;

import com.subscriptionmanager.dto.CategoryDTO;
import com.subscriptionmanager.entity.Category;
import com.subscriptionmanager.entity.User;
import com.subscriptionmanager.exception.EntityNotFoundException;
import com.subscriptionmanager.repository.CategoryRepository;
import com.subscriptionmanager.repository.UserRepository;
import com.subscriptionmanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public Category create(CategoryDTO categoryDTO) {
        Long userId = categoryDTO.getUserId();
        User user = userRepository.findActiveById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", "id", userId));

        Category category = new Category();
        category.setUser(user);
        category.setCategoryName(categoryDTO.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findActiveById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category", "id", categoryId));

        User user = userRepository.findActiveById(categoryDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User", "id", categoryDTO.getUserId()));

        category.setCategoryName(categoryDTO.getCategoryName());
        category.setUser(user);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
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
    public List<Category> getByUserId(Long userId) {
        return categoryRepository.findAll().stream()
                .filter(category -> category.isActive() && category.getUser().getUserId().equals(userId))
                .collect(Collectors.toList());
    }


    @Override
    public Category getById(Long categoryId) {
        return categoryRepository.findActiveById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category", "id", categoryId));
    }
}