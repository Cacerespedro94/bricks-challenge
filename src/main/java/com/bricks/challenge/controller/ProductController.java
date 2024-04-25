package com.bricks.challenge.controller;

import com.bricks.challenge.assembler.ProductAssembler;
import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.request.ProductRequestFilterDto;
import com.bricks.challenge.dto.request.ProductUpdateDto;
import com.bricks.challenge.dto.response.ProductModel;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.entity.ProductEntity;
import com.bricks.challenge.service.CategoryService;
import com.bricks.challenge.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/bricks-challenge/product")
public class ProductController {
    private final ProductService productService;
    private final ProductAssembler productAssembler;
    private final PagedResourcesAssembler<ProductEntity> pagedResourcesAssembler;

    public ProductController(ProductService productService, ProductAssembler productAssembler, PagedResourcesAssembler<ProductEntity> pagedResourcesAssembler, CategoryService categoryService) {
        this.productService = productService;
        this.productAssembler = productAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> saveProduct(@Valid @RequestBody ProductRequestDto dto) {
        ProductResponseDto savedProduct = productService.saveProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping()
    public ResponseEntity<PagedModel<ProductModel>> getProductsPaginated(ProductRequestFilterDto filterDto, Pageable pageable) {
        Page<ProductEntity> productEntityPage = productService.getProductsPaginated(filterDto, pageable);
        PagedModel<ProductModel> products = pagedResourcesAssembler.toModel(productEntityPage, productAssembler);
        return ResponseEntity.ok(products);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductUpdateDto dto) {
        return ResponseEntity.ok(productService.updateProduct(dto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
