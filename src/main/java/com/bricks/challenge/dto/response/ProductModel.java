package com.bricks.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel extends RepresentationModel<ProductModel> {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private CategoryDto category;
}
