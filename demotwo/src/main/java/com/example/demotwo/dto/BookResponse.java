package com.example.demotwo.dto;

import com.example.demotwo.repository.entity.Category;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private String title;
    private String author;
    private int yearPublished;
    private List<CategorySchema> categories;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategorySchema {
        private String name;
        private String description;
    }
}
