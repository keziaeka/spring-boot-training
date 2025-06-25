package com.example.demotwo.dto;

import com.example.demotwo.repository.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private int yearPublished;
    private List<Long> categoryIds;
}
