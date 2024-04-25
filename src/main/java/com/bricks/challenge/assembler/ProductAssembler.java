package com.bricks.challenge.assembler;


import com.bricks.challenge.controller.ProductController;
import com.bricks.challenge.dto.response.ProductModel;
import com.bricks.challenge.entity.ProductEntity;
import com.bricks.challenge.mapper.ProductMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductAssembler extends RepresentationModelAssemblerSupport<ProductEntity, ProductModel> {

    private final ProductMapper productMapper;


    public ProductAssembler(ProductMapper productMapper) {
        super(ProductController.class, ProductModel.class);
        this.productMapper = productMapper;
    }

    @Override
    public ProductModel toModel(ProductEntity entity) {
        return productMapper.toModel(entity);
    }
}