package com.subscriptionmanager.domain.mapper;

import com.subscriptionmanager.domain.dto.CategoryDTO;
import com.subscriptionmanager.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryDTO dto);

    CategoryDTO toDTO(Category entity);

    void updateFromDTO(CategoryDTO dto, @MappingTarget Category entity);
}
