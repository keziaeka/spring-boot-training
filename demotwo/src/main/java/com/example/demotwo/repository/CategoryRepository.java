package com.example.demotwo.repository;

import com.example.demotwo.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
