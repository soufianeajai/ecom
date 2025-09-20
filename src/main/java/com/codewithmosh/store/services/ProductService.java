package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.product.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts(Byte categoryId);
    ProductDto getProductById(Long id);
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto, Long id);
    void deleteProduct(Long id);
}
