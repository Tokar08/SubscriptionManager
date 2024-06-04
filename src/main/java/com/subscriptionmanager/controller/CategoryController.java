package com.subscriptionmanager.controller;

import com.subscriptionmanager.dto.CategoryDTO;
import com.subscriptionmanager.entity.Category;
import com.subscriptionmanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.create(categoryDTO);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Category>> getByUserId(@PathVariable Long userId) {
        List<Category> categories = categoryService.getByUserId(userId);
        return ResponseEntity.ok(categories);
    }
}
