package com.bricks.challenge.mapper;

import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.request.ProductUpdateDto;
import com.bricks.challenge.dto.response.CategoryDto;
import com.bricks.challenge.dto.response.ProductModel;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.entity.ProductEntity;
import com.bricks.challenge.service.CategoryService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductRequestDto productRequestDto);
    ProductModel toModel (ProductEntity productEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductEntity(
            final ProductUpdateDto dto,
            @MappingTarget ProductEntity entity);


}
