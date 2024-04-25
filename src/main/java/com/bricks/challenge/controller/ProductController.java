package com.bricks.challenge.controller;

import com.bricks.challenge.assembler.ProductAssembler;
import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.request.ProductRequestFilterDto;
import com.bricks.challenge.dto.response.ProductModel;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.entity.ProductEntity;
import com.bricks.challenge.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bricks-challenge/product")
public class ProductController {
    private final ProductService productService;
    private final ProductAssembler productAssembler;
    private final PagedResourcesAssembler<ProductEntity> pagedResourcesAssembler;

    public ProductController(ProductService productService, ProductAssembler productAssembler, PagedResourcesAssembler<ProductEntity> pagedResourcesAssembler) {
        this.productService = productService;
        this.productAssembler = productAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping()
    public ProductResponseDto saveProduct(@RequestBody ProductRequestDto dto) {
        return productService.saveProduct(dto);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @GetMapping()
    public PagedModel<ProductModel> getProductsPaginated(ProductRequestFilterDto filterDto, Pageable pageable) {
        Page<ProductEntity> productEntityPage = productService.getProductsPaginated(filterDto, pageable);
        return pagedResourcesAssembler.toModel(productEntityPage, productAssembler);
    }

}
