package com.subscriptionmanager.service;

import com.subscriptionmanager.dto.CategoryDTO;
import com.subscriptionmanager.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(CategoryDTO categoryDTO);
    Category update(Long categoryId, CategoryDTO categoryDTO);
    void delete(Long categoryId);
    List<Category> getAll();
    List<Category> getByUserId(Long userId);
    Category getById(Long categoryId);
}
