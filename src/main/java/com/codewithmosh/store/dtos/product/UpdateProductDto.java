package com.codewithmosh.store.dtos.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UpdateProductDto {
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    @DecimalMin(value = "0.01", message = "Price must be a positive number")
    private BigDecimal price;
    private Byte categoryId;
}
