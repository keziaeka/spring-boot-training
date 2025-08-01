package com.example.demofive.demofive.service;

import com.example.demofive.demofive.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public List<Product> getAllProducts() {
        // This method should return a list of all products.
        // For now, we will return an empty list as a placeholder.
        return List.of();
    }
}
