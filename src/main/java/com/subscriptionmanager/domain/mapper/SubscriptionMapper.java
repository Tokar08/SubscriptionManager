package com.subscriptionmanager.domain.mapper;

import com.subscriptionmanager.domain.dto.SubscriptionDTO;
import com.subscriptionmanager.domain.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(source = "category.categoryId", target = "categoryId")
    SubscriptionDTO toDTO(Subscription entity);

    @Mapping(source = "categoryId", target = "category.categoryId")
    Subscription toEntity(SubscriptionDTO dto);

    @Mapping(source = "categoryId", target = "category.categoryId")
    void updateFromDTO(SubscriptionDTO dto, @MappingTarget Subscription entity);
}