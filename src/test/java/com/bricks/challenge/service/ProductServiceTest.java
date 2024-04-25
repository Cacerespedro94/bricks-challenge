package com.bricks.challenge.service;

import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.request.ProductUpdateDto;
import com.bricks.challenge.dto.response.CategoryDto;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.entity.ProductEntity;
import com.bricks.challenge.exceptions.ProductNotFoundException;
import com.bricks.challenge.mapper.ProductMapper;
import com.bricks.challenge.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("Should save a product")
    void saveProduct_ok() {
        when(productRepository.save(any(ProductEntity.class))).thenReturn(getProductEntityMock());
        when(categoryService.getCategoryById(anyInt())).thenReturn(getCategoryDto());
        when(productMapper.toEntity(any(ProductRequestDto.class))).thenReturn(getProductEntityMock());
        when(productMapper.toDto(any(ProductEntity.class))).thenReturn(getProductResponseDtoMock());

        ProductResponseDto productEntity = productService.saveProduct(getProductRequestDto());

        verify(productRepository, times(1)).save(any(ProductEntity.class));
        verify(categoryService, times(1)).getCategoryById(anyInt());

        verifyNoMoreInteractions(productRepository);
        verifyNoMoreInteractions(categoryService);

        assertNotNull(productEntity);
    }

    @Test
    void getProductById_ok() {
        when(productRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(getProductEntityMock()));
        when(productMapper.toDto(any(ProductEntity.class))).thenReturn(getProductResponseDtoMock());
        when(categoryService.getCategoryById(anyInt())).thenReturn(getCategoryDto());

        ProductResponseDto productDto = productService.getProductById(1);

        assertNotNull(productDto);

        verify(productRepository, times(1)).findById(anyInt());
        verify(categoryService, times(1)).getCategoryById(anyInt());
        verify(productMapper, times(1)).toDto(any(ProductEntity.class));

        verifyNoMoreInteractions(productRepository);
        verifyNoMoreInteractions(categoryService);
        verifyNoMoreInteractions(productMapper);
    }

    @Test
    void getProductById_notFound() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(1);
        });

        verify(productRepository, times(1)).findById(anyInt());

        verifyNoMoreInteractions(productRepository);
        verifyNoInteractions(categoryService);
        verifyNoInteractions(productMapper);
    }

    @Test
    void updateProduct_success() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(getProductEntityMock()));
        when(productMapper.toDto(any(ProductEntity.class))).thenReturn(getProductResponseDtoMock());
        when(categoryService.getCategoryById(anyInt())).thenReturn(getCategoryDto());
        when(productRepository.saveAndFlush(any(ProductEntity.class))).thenReturn(getProductEntityMock());

        ProductResponseDto resultDto = productService.updateProduct(getUpdateDtoMock());
        assertNotNull(resultDto);
        assertEquals("Updated name", resultDto.getName());

        verify(productRepository, times(1)).saveAndFlush(any(ProductEntity.class));
    }

    @Test
    void deleteProduct_success() {
        when(productRepository.findById(any(Integer.class))).thenReturn(Optional.of(getProductEntityMock()));
        doNothing().when(productRepository).delete(any(ProductEntity.class));
        assertDoesNotThrow(() -> productService.deleteProduct(1));
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).delete(getProductEntityMock());
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void deleteProduct_productNotFound() {
        when(productRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1));

        verify(productRepository, never()).delete(any(ProductEntity.class));
    }

    @Test
    void updateProduct_productNotFound() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(getUpdateDtoMock()));

        verify(productRepository, never()).saveAndFlush(any(ProductEntity.class));
    }



    private ProductEntity getProductEntityMock() {
        return ProductEntity.builder()
                .id(1)
                .categoryId(10)
                .name("Product name")
                .price(BigDecimal.valueOf(840.3))
                .stock(10)
                .build();
    }

    private CategoryDto getCategoryDto() {
        return CategoryDto.builder()
                .id(10)
                .name("Category Name")
                .code("cod")
                .description("Description")
                .build();
    }

    private ProductRequestDto getProductRequestDto() {
        return ProductRequestDto.builder()
                .categoryId(10)
                .name("Product name")
                .price(BigDecimal.valueOf(840.3))
                .stock(10)
                .build();
    }

    private ProductResponseDto getProductResponseDtoMock() {
        return ProductResponseDto.builder()
                .id(1)
                .name("Updated name")
                .price(BigDecimal.valueOf(840.3))
                .stock(10)
                .category(getCategoryDto())
                .build();
    }

    private ProductUpdateDto getUpdateDtoMock() {
        return ProductUpdateDto.builder()
                .id(1)
                .name("Product Name")
                .price(BigDecimal.valueOf(840.3))
                .stock(10)
                .build();
    }
}
