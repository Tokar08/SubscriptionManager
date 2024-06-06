package com.subscriptionmanager.service;

import com.subscriptionmanager.dto.CategoryDTO;
import com.subscriptionmanager.entity.Category;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category create(Jwt jwt, CategoryDTO categoryDTO);
    Category update(UUID categoryId, CategoryDTO categoryDTO);
    void delete(UUID categoryId);
    List<Category> getAll();
    List<Category> getByUserId(Jwt jwt);
    Category getById(UUID categoryId);
}
