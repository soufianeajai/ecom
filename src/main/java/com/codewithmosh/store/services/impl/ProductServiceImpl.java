package com.codewithmosh.store.services.impl;

import com.codewithmosh.store.dtos.product.*;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.exceptions.InvalidCategoryException;
import com.codewithmosh.store.exceptions.ProductNotFoundException;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import com.codewithmosh.store.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts(Byte categoryId){
        List<Product> products = categoryId == null ? productRepository.findAllWithCategoryId() : productRepository.findAllByCategoryId(categoryId);
        var pros = products.stream().map(productMapper::toDto).toList();
        if (pros.isEmpty()) throw new ProductNotFoundException(categoryId);
        return pros;

    }
    public ProductDto getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toDto(product);
    }
    public ProductDto createProduct(CreateProductDto productDto){
        Product product = productMapper.toEntity(productDto);
        if (product.getCategory() == null) throw new InvalidCategoryException();
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }
    @Transactional
    public ProductDto updateProduct(UpdateProductDto productDto, Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productMapper.updateProductFromDto(productDto, product);
        return productMapper.toDto(product);
    }
    @Transactional
    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }
}
