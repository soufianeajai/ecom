package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.product.CreateProductDto;
import com.codewithmosh.store.dtos.product.ProductDto;
import com.codewithmosh.store.dtos.product.UpdateProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts(Byte categoryId);
    ProductDto getProductById(Long id);
    ProductDto createProduct(CreateProductDto productDto);
    ProductDto updateProduct(UpdateProductDto productDto, Long id);
    void deleteProduct(Long id);
}
