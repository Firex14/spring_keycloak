package com.yaba.springkeycloak.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRequestException extends RuntimeException{
    private final int code;
    private final ExceptionLevel level;
    private final HttpStatus httpStatus;

    public ApiRequestException(String message, int code, ExceptionLevel level) {
        super(message);

        this.code = code;
        this.level = level;
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public ApiRequestException(String message, int code, ExceptionLevel level, HttpStatus httpStatus) {
        super(message);

        this.code = code;
        this.level = level;
        this.httpStatus = httpStatus;
    }
}
