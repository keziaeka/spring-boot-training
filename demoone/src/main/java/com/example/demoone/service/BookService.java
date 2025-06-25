package com.example.demoone.service;

import com.example.demoone.BookNotFoundException;
import com.example.demoone.dto.BookRequest;
import com.example.demoone.dto.BookResponse;
import com.example.demoone.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
public class BookService {

    @Autowired
    ObjectMapper mapper;

    List<Book> books = new ArrayList<Book>();

    BookService(){
        init();
    }

    private void init(){
        Book bookOne = new Book();
        bookOne.setId(000000L);
        bookOne.setTitle("FirstBook");
        bookOne.setAuthor("Zeze");

        Book bookTwo = new Book();
        bookTwo.setId(000001L);
        bookTwo.setTitle("SecondBook");
        bookTwo.setAuthor("Zeze");

        Book bookThree = new Book();
        bookThree.setId(000002L);
        bookThree.setTitle("ThirdBook");
        bookThree.setAuthor("Zeze");

        books.add(bookOne);
        books.add(bookTwo);
        books.add(bookThree);
    }

    public Flux<BookResponse> getAllBooks(@RequestHeader HttpHeaders httpHeaders) {
        //Example List reactive version using Flux <>
        try {
            List<BookResponse> responses = new ArrayList<>();
            for (Book book : books) {
                responses.add(toResponse(book));
            }

            log.info(responses.toString());
            return Flux.fromIterable(responses);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Warning");
        }
    }

    public Mono<ResponseEntity<String>> getBookById(@RequestHeader HttpHeaders httpHeaders, long id) {
        try {

            return Mono.just(
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(books.stream()
                                    .filter(book -> book.getId().equals(id))
                                    .findFirst()
                                    .orElseThrow(() -> new BookNotFoundException(id))))
            );
        } catch (Exception ex) {
            return Mono.just(
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("Failed with message: " + ex.getMessage())
            );
        }
    }

    public Mono<ResponseEntity<String>> deleteBooksById(@RequestHeader HttpHeaders httpHeaders, Long id){
        try {
            int index = IntStream.range(0, books.size())
                    .filter(i -> books.get(i).getId().equals(id))
                    .findFirst()
                    .orElse(-1);

            books.remove(index);

            return Mono.just(
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("Success. Book deleted")
            );
        } catch (Exception ex) {
            return Mono.just(
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("Failed with message: " + ex.getMessage())
            );
        }
    }

    public Mono<ResponseEntity<String>> updateBooksById(@RequestHeader HttpHeaders httpHeaders, Long id, BookRequest book){
        try {
            //Example Response Entity reactive version using Mono<>
            int index = IntStream.range(0, books.size())
                    .filter(i -> books.get(i).getId().equals(id))
                    .findFirst()
                    .orElse(-1);

            books.get(index).setAuthor(book.getAuthor());
            books.get(index).setTitle(book.getTitle());

            return Mono.just(
                    ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Success. Book updated"));
        } catch (Exception ex) {
            return Mono.just(
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("Failed with message: " + ex.getMessage())
            );
        }
    }

    public Mono<ResponseEntity<String>> insertBook(@RequestHeader HttpHeaders httpHeaders, BookRequest book){
        try {
            books.add(Book.builder()
                    .id((long) books.size())
                    .author(book.getAuthor())
                    .title(book.getTitle())
                    .build());

            return Mono.just(
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("Success. Book inserted")
            );
        } catch (Exception ex) {
            return Mono.just(
                    ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("Failed with message: " + ex.getMessage())
            );
        }
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor());
    }
}

