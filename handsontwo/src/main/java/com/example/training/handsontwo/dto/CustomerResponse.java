package com.example.training.handsontwo.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponse {
    private String name;
    private String email;
    private double balance; // Assuming balance is a double, adjust as necessary
}
