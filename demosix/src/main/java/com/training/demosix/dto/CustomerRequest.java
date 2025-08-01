package com.training.demosix.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerRequest {
    private String name;
    private String email;
    private Double balance;
}
