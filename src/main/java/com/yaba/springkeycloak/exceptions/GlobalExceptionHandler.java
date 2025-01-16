package com.yaba.springkeycloak.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException ex) {
        ApiException errorResponse = new ApiException(
                ex.getMessage(),
                ex.getCode(),
                ex.getLevel()
        );

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}
