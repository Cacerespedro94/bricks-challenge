package com.bricks.challenge.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDto {
    @NotNull
    private Integer id;
    private String name;
    @Min(value = 0)
    private BigDecimal price;
    @Min(value = 0)
    private Integer stock;
    private String categoryId;
}
