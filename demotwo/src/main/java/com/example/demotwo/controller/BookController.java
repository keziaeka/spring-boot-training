package com.example.demotwo.controller;

import com.example.demotwo.dto.BookRequest;
import com.example.demotwo.dto.BookResponse;
import com.example.demotwo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/")
    public List<BookResponse> getAllBooks() {
        return bookService.getAll();
    }

    @PostMapping("/")
    public String postBooks(@RequestBody BookRequest bookRequest){
        bookService.save(bookRequest);
        return "success";
    }

    @PostMapping("/with-category")
    public String postBooksWithCategory(@RequestBody BookRequest bookRequest) {
        return bookService.addBookWithCategory(bookRequest);
    }


}
