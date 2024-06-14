package com.subscriptionmanager.domain.service.impl;

import com.subscriptionmanager.domain.dto.CategoryDTO;
import com.subscriptionmanager.domain.entity.Category;
import com.subscriptionmanager.domain.mapper.CategoryMapper;
import com.subscriptionmanager.exception.handler.DataEntityNotFoundException;
import com.subscriptionmanager.domain.repository.CategoryRepository;
import com.subscriptionmanager.domain.service.CategoryService;
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
    private final CategoryMapper categoryMapper;

    @Override
    public Category create(Jwt jwt, CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        category.setUserId(UUID.fromString(jwt.getSubject()));
        return categoryRepository.save(category);
    }

    @Override
    public Category update(UUID categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findActiveById(categoryId)
                .orElseThrow(() -> new DataEntityNotFoundException("Category", "id", categoryId));

        categoryMapper.updateFromDTO(categoryDTO, category);

        return categoryRepository.save(category);
    }

    @Override
    public void delete(UUID categoryId) {
        Category category = categoryRepository.findActiveById(categoryId)
                .orElseThrow(() -> new DataEntityNotFoundException("Category", "id", categoryId));
        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findByIsActiveTrue();
    }

    @Override
    public Category getById(UUID categoryId) {
        return categoryRepository.findActiveById(categoryId)
                .orElseThrow(() -> new DataEntityNotFoundException("Category", "id", categoryId));
    }
}