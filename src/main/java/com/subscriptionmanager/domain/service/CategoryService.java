package com.subscriptionmanager.domain.service;

import com.subscriptionmanager.web.dto.CategoryDTO;
import com.subscriptionmanager.domain.entity.Category;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category create(Jwt jwt, CategoryDTO categoryDTO);
    Category update(UUID categoryId, CategoryDTO categoryDTO);
    void delete(UUID categoryId);
    List<Category> getAll();
    Category getById(UUID categoryId);
}
