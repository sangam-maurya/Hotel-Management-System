package com.example.controller;

import com.example.Service.ProductService;
import com.example.entity.Product;
import com.example.payload.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/version/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequest) {
        // Use the fields from ProductRequest to update the product
        Product product = productService.updateProduct(id, productRequest);
        return product;
    }
}
