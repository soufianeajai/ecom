package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Autowired
    public ProductController(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>>  getAllProducts(@RequestParam(required = false, name = "categoryId") Byte categoryId){
         List<Product> products = categoryId == null ? productRepository.findAllWithCategoryId() : productRepository.findAllByCategoryId(categoryId);
         var pros = products.stream().map(productMapper::toDto).toList();
         return pros.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(pros);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return productRepository.findById(id)
                .map(productMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, UriComponentsBuilder uri){
        Product product = productMapper.toEntity(productDto);
        if (product.getCategory() == null) return ResponseEntity.badRequest().build();
        Product productSaved = productRepository.save(product);
        var path = uri.path("/products/{id}").buildAndExpand(productSaved.getId()).toUri();
        return ResponseEntity.created(path).body(productMapper.toDto(productSaved));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id){
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return ResponseEntity.notFound().build();
        productMapper.updateProductFromDto(productDto, product);
        System.out.println(product);
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return ResponseEntity.notFound().build();
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
