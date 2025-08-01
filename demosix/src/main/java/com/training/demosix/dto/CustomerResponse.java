package com.training.demosix.dto;

import lombok.*;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private Double balance;
    private String message;
}
