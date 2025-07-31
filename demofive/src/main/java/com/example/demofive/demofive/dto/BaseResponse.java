package com.example.demofive.demofive.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(String message, int code, T data) {
        return new BaseResponse<>(true, code, message, data);
    }
}
