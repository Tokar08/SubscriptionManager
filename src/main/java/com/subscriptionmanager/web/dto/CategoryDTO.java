package com.subscriptionmanager.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {

    @NotBlank(message = "Category name is required")
    @Size(min = 1, max = 255, message = "Category name must be between 1 and 255 characters")
    private String categoryName;
}
