package com.codewithmosh.store.services.impl;

import com.codewithmosh.store.dtos.product.ProductDto;
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
        if (pros.isEmpty()) throw new ProductNotFoundException("Products with the Category Id " + categoryId + " are not found");
        return pros;

    }
    public ProductDto getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with the Id " + id + " is not found"));
        return productMapper.toDto(product);
    }
    public ProductDto createProduct(ProductDto productDto){
        Product product = productMapper.toEntity(productDto);
        if (product.getCategory() == null) throw new InvalidCategoryException("Invalid Category");
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }
    @Transactional
    public ProductDto updateProduct(ProductDto productDto, Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with the Id " + id + " is not found"));
        productMapper.updateProductFromDto(productDto, product);
        return productMapper.toDto(product);
    }
    @Transactional
    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with the Id " + id + " is not found"));
        productRepository.delete(product);
    }
}
