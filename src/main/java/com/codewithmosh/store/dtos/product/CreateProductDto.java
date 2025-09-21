package com.codewithmosh.store.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class CreateProductDto {
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;
    @NotBlank(message = "Product description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.01", message = "Price must be a positive number")
    private BigDecimal price;
    private Byte categoryId;
}
