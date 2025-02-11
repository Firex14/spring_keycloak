package com.yaba.springkeycloak.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseUtils {

    // Constructeur privé pour empêcher l'instanciation
    private ResponseUtils() { }


    public static <T> ResponseEntity<CustomApiResponse<T>> buildSuccessResponse(
            T data, String message, HttpStatus status) {
        CustomApiResponse<T> customApiResponse = new CustomApiResponse<>(true, data, message, status.value());
        return ResponseEntity.status(status).body(customApiResponse);
    }

    public static <T> ResponseEntity<CustomApiResponse<T>> buildErrorResponse(
            String errorMessage, Map<String, String> errors, HttpStatus status) {
        if (errors == null) {
            errors = new HashMap<>();
        }

        CustomApiResponse<T> customApiResponse = new CustomApiResponse<>(false, null, errorMessage, status.value());
        customApiResponse.setErrors(errors);
        return ResponseEntity.status(status).body(customApiResponse);
    }


    public static ResponseEntity<CustomApiResponse<Void>> buildVoidResponse(
            String message, HttpStatus status) {
        CustomApiResponse<Void> customApiResponse = new CustomApiResponse<>(true,null, message, status.value());
        return ResponseEntity.status(status).body(customApiResponse);
    }
}
