package com.bricks.challenge.service;

import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.response.ProductResponseDto;

public interface ProductService {
    Object saveProduct(ProductRequestDto dto);

    ProductResponseDto getProductById(Integer productId);
}
