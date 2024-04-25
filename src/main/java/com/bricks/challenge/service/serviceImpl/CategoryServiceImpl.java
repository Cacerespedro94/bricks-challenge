package com.bricks.challenge.service.serviceImpl;

import com.bricks.challenge.dto.response.CategoryDto;
import com.bricks.challenge.service.CategoryService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final RestTemplate restTemplate;

    public CategoryServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    @Cacheable("categories")
    public List<CategoryDto> getCategories() {
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(
                "https://api.develop.bricks.com.ar/loyalty/category/producer",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDto>>() {
                }
        );
        return response.getBody();
    }

    @Override
    @Cacheable(value = "categories", key = "#categoryId")
    public CategoryDto getCategoryById(Integer categoryId) {
        List<CategoryDto> categories = getCategories();
        return categories.stream()
                .filter(category -> category.getId().equals(categoryId))
                .findFirst()
                .orElse(null);
    }
}
