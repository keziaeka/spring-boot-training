package com.example.demofive.demofive.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Product {
    private Long id;
    private String name;
    private Integer stock;
}
