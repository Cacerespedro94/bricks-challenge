package com.bricks.challenge.service.serviceImpl;

import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.request.ProductRequestFilterDto;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.entity.ProductEntity;
import com.bricks.challenge.mapper.ProductMapper;
import com.bricks.challenge.repository.ProductRepository;
import com.bricks.challenge.repository.ProductSpecification;
import com.bricks.challenge.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto dto) {
        ProductEntity productEntity = productRepository.save(productMapper.toEntity(dto));
        return productMapper.toDto(productEntity);
    }

    @Override
    public ProductResponseDto getProductById(Integer productId) {
        return productMapper.toDto(productRepository.findById(productId).get());
    }

    @Override
    public Page<ProductEntity> getProductsPaginated(ProductRequestFilterDto filterDto, Pageable pageable) {
        return productRepository.findAll(ProductSpecification.getProductsFilter(filterDto), pageable);
    }
}
