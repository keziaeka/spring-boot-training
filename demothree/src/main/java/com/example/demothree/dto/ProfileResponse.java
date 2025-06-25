package com.example.demothree.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {
    private String fullName;
    private String email;
    private String phone;
}
