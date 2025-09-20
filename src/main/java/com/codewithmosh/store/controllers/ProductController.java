package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.product.ProductDto;
import com.codewithmosh.store.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>>  getAllProducts(@RequestParam(required = false, name = "categoryId") Byte categoryId){
        return ResponseEntity.ok(productService.getAllProducts(categoryId));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, UriComponentsBuilder uri){
        ProductDto product = productService.createProduct(productDto);
        var path = uri.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(path).body(product);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id){
        ProductDto product = productService.updateProduct(productDto, id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
