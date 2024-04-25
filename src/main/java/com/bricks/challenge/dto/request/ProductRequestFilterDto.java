package com.bricks.challenge.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestFilterDto {
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Integer categoryId;
}
