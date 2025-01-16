package com.yaba.springkeycloak.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;
    private int status;
    private Map<String, String> errors;

    public ApiResponse(boolean success, T data, String message, int status) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.status = status;
        this.errors = new HashMap<>();
    }

}
