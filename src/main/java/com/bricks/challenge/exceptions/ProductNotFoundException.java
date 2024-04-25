package com.bricks.challenge.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(Integer productId){
        super("Product not found with ID: " + productId);
    }
}
