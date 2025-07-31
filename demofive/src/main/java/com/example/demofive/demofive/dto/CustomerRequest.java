package com.example.demofive.demofive.dto;

import com.example.demofive.demofive.validator.UniqueEmail;
import com.example.demofive.demofive.validator.UniquePhoneNumber;
import lombok.Data;

@Data
public class CustomerRequest {
    private String customerName;
    private String customerAddress;
    @UniqueEmail
    private String email;
    @UniquePhoneNumber
    private String phoneNumber;
}
