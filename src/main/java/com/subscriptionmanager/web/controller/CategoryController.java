package com.subscriptionmanager.web.controller;

import com.subscriptionmanager.domain.dto.CategoryDTO;
import com.subscriptionmanager.domain.entity.Category;
import com.subscriptionmanager.domain.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@AuthenticationPrincipal Jwt jwt,
                                           @Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.create(jwt, categoryDTO);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id:[0-9a-fA-F\\-]+}")
    public ResponseEntity<Category> update(@PathVariable("id") UUID id,
                                           @Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id:[0-9a-fA-F\\-]+}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id:[0-9a-fA-F\\-]+}")
    public ResponseEntity<Category> getById(@PathVariable("id") UUID id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }
}
