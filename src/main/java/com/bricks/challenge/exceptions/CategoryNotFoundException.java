package com.bricks.challenge.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(Integer categoryId){
        super("Category not found with ID: " + categoryId);
    }
}
