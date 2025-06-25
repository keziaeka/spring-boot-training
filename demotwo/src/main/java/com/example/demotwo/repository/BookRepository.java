package com.example.demotwo.repository;

import com.example.demotwo.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
