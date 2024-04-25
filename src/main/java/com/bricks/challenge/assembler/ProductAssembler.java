package com.bricks.challenge.assembler;


import com.bricks.challenge.controller.ProductController;
import com.bricks.challenge.dto.response.CategoryDto;
import com.bricks.challenge.dto.response.ProductModel;
import com.bricks.challenge.entity.ProductEntity;
import com.bricks.challenge.mapper.ProductMapper;
import com.bricks.challenge.service.CategoryService;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductAssembler extends RepresentationModelAssemblerSupport<ProductEntity, ProductModel> {

    private final ProductMapper productMapper;
    private final CategoryService categoryService;


    public ProductAssembler(ProductMapper productMapper, CategoryService categoryService) {
        super(ProductController.class, ProductModel.class);
        this.productMapper = productMapper;

        this.categoryService = categoryService;
    }

    @Override
    public ProductModel toModel(ProductEntity entity) {
        ProductModel productModel = productMapper.toModel(entity);
        CategoryDto categoryDto = categoryService.getCategoryById(entity.getCategoryId());
        productModel.setCategory(categoryDto);
        return productModel;
    }
}