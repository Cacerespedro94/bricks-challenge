package com.bricks.challenge.controller;

import com.bricks.challenge.dto.request.ProductRequestDto;
import com.bricks.challenge.dto.response.ProductResponseDto;
import com.bricks.challenge.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bricks-challenge/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public Object saveProduct(@RequestBody ProductRequestDto dto) {
        return productService.saveProduct(dto);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }
}
