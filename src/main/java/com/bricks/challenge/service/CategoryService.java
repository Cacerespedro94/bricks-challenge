package com.bricks.challenge.service;

import com.bricks.challenge.dto.response.CategoryDto;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CategoryService {
    @Cacheable("categories")
    List<CategoryDto> getCategories();

    @Cacheable(value = "categories", key = "#categoryId")
    CategoryDto getCategoryById(Integer categoryId);
}
