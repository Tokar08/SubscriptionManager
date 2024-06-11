package com.subscriptionmanager.web.controller;

import com.subscriptionmanager.domain.dto.CategoryDTO;
import com.subscriptionmanager.domain.entity.Category;
import com.subscriptionmanager.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@AuthenticationPrincipal Jwt jwt, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.create(jwt, categoryDTO);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<Category> update(@PathVariable("id") UUID id, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Category> getById(@PathVariable("id") UUID id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Category>> getByUserId(@AuthenticationPrincipal Jwt jwt) {
        List<Category> categories = categoryService.getByUserId(jwt);
        return ResponseEntity.ok(categories);
    }
}