package com.example.demotwo.controller;

import com.example.demotwo.dto.CategoryRequest;
import com.example.demotwo.repository.CategoryRepository;
import com.example.demotwo.repository.entity.Category;
import com.example.demotwo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping("/")
    public String postCategory(@RequestBody CategoryRequest categoryRequest) {
        // This method should handle the creation of a new category
        // For now, we can return a success message

        return categoryService.postCategory(categoryRequest);
    }

}
