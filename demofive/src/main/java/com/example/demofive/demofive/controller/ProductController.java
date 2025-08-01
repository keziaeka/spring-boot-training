package com.example.demofive.demofive.controller;

import com.example.demofive.demofive.entity.Product;
import com.example.demofive.demofive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Define endpoints for product operations here
    // For example, you can add methods to handle CRUD operations for products

    // Example method to get all products
     @GetMapping
     public List<Product> getAllProducts() {
         return productService.getAllProducts();
     }

    // Example method to get a product by ID
    // @GetMapping("/{id}")
    // public Product getProductById(@PathVariable Long id) {
    //     return productService.getProductById(id);
    // }
}
