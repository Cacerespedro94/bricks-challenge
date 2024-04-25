package com.bricks.challenge.service.serviceImpl;

import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.request.ProductRequestFilterDto;
import com.bricks.challenge.dto.request.ProductUpdateDto;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.entity.ProductEntity;
import com.bricks.challenge.exceptions.ProductNotFoundException;
import com.bricks.challenge.mapper.ProductMapper;
import com.bricks.challenge.repository.ProductRepository;
import com.bricks.challenge.repository.ProductSpecification;
import com.bricks.challenge.service.CategoryService;
import com.bricks.challenge.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto dto) {
        ProductEntity productEntity = productRepository.save(productMapper.toEntity(dto));
        ProductResponseDto productDto = productMapper.toDto(productEntity);
        productDto.setCategory(categoryService.getCategoryById(productEntity.getCategoryId()));
        return productDto;
    }

    @Override
    public ProductResponseDto getProductById(Integer productId) {
        ProductEntity productEntity = getProductEntityById(productId);
        ProductResponseDto productDto = productMapper.toDto(productEntity);
        productDto.setCategory(categoryService.getCategoryById(productEntity.getCategoryId()));
        return productDto;
    }

    @Override
    public Page<ProductEntity> getProductsPaginated(ProductRequestFilterDto filterDto, Pageable pageable) {
        return productRepository.findAll(ProductSpecification.getProductsFilter(filterDto), pageable);
    }

    @Override
    public ProductResponseDto updateProduct(ProductUpdateDto dto) {
        ProductEntity productEntity = getProductEntityById(dto.getId());
        productMapper.updateProductEntity(dto, productEntity);
        productRepository.saveAndFlush(productEntity);

        return productMapper.toDto(productEntity);
    }

    @Override
    public void deleteProduct(Integer productId) {
        ProductEntity productEntity = getProductEntityById(productId);
        productRepository.delete(productEntity);
    }

    private ProductEntity getProductEntityById(Integer productId){
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
