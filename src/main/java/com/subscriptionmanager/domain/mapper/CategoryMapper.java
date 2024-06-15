package com.subscriptionmanager.domain.mapper;

import com.subscriptionmanager.web.dto.CategoryDTO;
import com.subscriptionmanager.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDTO dto);

    CategoryDTO toDTO(Category entity);

    void updateFromDTO(CategoryDTO dto, @MappingTarget Category entity);
}
