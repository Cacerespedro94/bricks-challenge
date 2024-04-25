package com.bricks.challenge.mapper;

import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductRequestDto productRequestDto);
}
