package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.product.*;
import com.codewithmosh.store.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("products")
@Validated
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
    public ResponseEntity<ProductDto> getProductById(@PathVariable @Positive Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody CreateProductDto productDto, UriComponentsBuilder uri){
        ProductDto product = productService.createProduct(productDto);
        var path = uri.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(path).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody UpdateProductDto productDto, @PathVariable @Positive Long id){
        ProductDto product = productService.updateProduct(productDto, id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Positive Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
