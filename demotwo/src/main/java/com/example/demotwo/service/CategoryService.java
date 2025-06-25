package com.example.demotwo.service;

import com.example.demotwo.dto.BookRequest;
import com.example.demotwo.dto.CategoryRequest;
import com.example.demotwo.repository.BookRepository;
import com.example.demotwo.repository.CategoryRepository;
import com.example.demotwo.repository.entity.Book;
import com.example.demotwo.repository.entity.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public String postCategory(CategoryRequest categoryRequest) {
        categoryRepository.save(Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription()).build());
        return "success";
    }
}
