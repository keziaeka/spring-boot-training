package com.example.demoone.controller;

import com.example.demoone.dto.BookRequest;
import com.example.demoone.dto.BookResponse;
import com.example.demoone.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController()
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public Flux<BookResponse> getAllBooks(@RequestHeader HttpHeaders httpHeaders) {
        log.info(String.valueOf(httpHeaders));
        Flux<BookResponse> responseEntityMono = bookService.getAllBooks(httpHeaders);
        return responseEntityMono;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBookById(@RequestHeader HttpHeaders  httpHeaders, @PathVariable Long id){
        return bookService.getBookById(httpHeaders, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooksById(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long id){
        return bookService.deleteBooksById(httpHeaders, id);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<String>> putBooksById(@RequestHeader HttpHeaders httpHeaders, @PathVariable Long id, @Valid @RequestBody BookRequest bookRequest){
        return bookService.updateBooksById(httpHeaders, id, bookRequest);
    }

    @PostMapping("/")
    public ResponseEntity<String> postBook(@RequestHeader HttpHeaders httpHeaders, @RequestBody BookRequest insertBook){
        return bookService.insertBook(httpHeaders, insertBook);
    }
}
