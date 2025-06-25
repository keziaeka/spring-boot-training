package com.example.demotwo.service;

import com.example.demotwo.dto.BookRequest;
import com.example.demotwo.dto.BookResponse;
import com.example.demotwo.repository.BookRepository;
import com.example.demotwo.repository.CategoryRepository;
import com.example.demotwo.repository.entity.Book;
import com.example.demotwo.repository.entity.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<BookResponse> getAll(){
        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            List<Category> categoryList = book.getCategories();
            List<BookResponse.CategorySchema> categorySchemaList = new ArrayList<>();
            if(!categoryList.isEmpty()){
                categorySchemaList = book.getCategories().stream()
                        .map(cat -> BookResponse.CategorySchema.builder()
                                .name(cat.getName())
                                .description(cat.getDescription())
                                .build())
                        .toList();
            } else {
                log.info("No categories found for book ID: " + book.getId());
            }

            bookResponseList.add(BookResponse.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .yearPublished(book.getYearPublished())
                    .categories(categorySchemaList)
                    .build());

            System.out.println("Book ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        }
        return bookResponseList;
    }

    public void save(BookRequest bookRequest){
        bookRepository.save(Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .yearPublished(bookRequest.getYearPublished()).build());
    }

    @Transactional
    public String addBookWithCategory(BookRequest bookRequest) {

        List<Long> categoryIds = bookRequest.getCategoryIds();
        List<Category> categorySet = new ArrayList<>();
        for (Long categoryId : categoryIds) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
            categorySet.add(category);
        }

        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .yearPublished(bookRequest.getYearPublished())
                .categories(categorySet)
                .build();

        bookRepository.save(book);
        return "Book added successfully with category ID: " + categoryIds;
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }
}
