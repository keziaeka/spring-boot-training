package com.example.demotwo.dto;

import com.example.demotwo.repository.entity.Book;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String name;
    private String description;
    private List<BookSchema> books;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookSchema{
        private String title;
        private String author;
        private int yearPublished;
    }
}
