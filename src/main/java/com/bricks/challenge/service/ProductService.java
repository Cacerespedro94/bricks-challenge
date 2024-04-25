package com.bricks.challenge.service;

import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.request.ProductRequestFilterDto;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDto saveProduct(ProductRequestDto dto);

    ProductResponseDto getProductById(Integer productId);

    Page<ProductEntity> getProductsPaginated(ProductRequestFilterDto filterDto, Pageable pageable);
}
