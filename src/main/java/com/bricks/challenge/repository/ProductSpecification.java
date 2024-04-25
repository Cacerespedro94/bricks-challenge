package com.bricks.challenge.repository;

import com.bricks.challenge.dto.request.ProductRequestFilterDto;
import com.bricks.challenge.entity.ProductEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<ProductEntity> getProductsFilter(ProductRequestFilterDto filterDto) {
        return (root, cq, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filterDto.getName() != null && !filterDto.getName().isEmpty()) {
                String searchString = "%" + filterDto.getName().toLowerCase() + "%";
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), searchString));
            }

            if (filterDto.getCategoryId() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("categoryId"), filterDto.getCategoryId()));
            }

            if (filterDto.getPrice() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("price"), filterDto.getPrice()));
            }

            if (filterDto.getStock() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("stock"), filterDto.getStock()));
            }

            return predicate;
        };
    }
}