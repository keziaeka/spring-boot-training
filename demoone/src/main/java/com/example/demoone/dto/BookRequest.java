package com.example.demoone.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequest {
    @NotBlank(message = "Judul tidak boleh kosong")
    private String title;

    @NotBlank(message = "Penulis tidak boleh kosong")
    private String author;
}

