package com.example.training.handsontwo.dto;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String email;
    private double balance = 0.0; // Default value set to 0.0
}
