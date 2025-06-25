package com.example.demoone.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @NotBlank
    private Long id;
    private String title = "";
    private String author = "";
}
