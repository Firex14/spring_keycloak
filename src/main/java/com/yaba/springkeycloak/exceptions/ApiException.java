package com.yaba.springkeycloak.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiException {
    private String message;
    private int code;
    private ExceptionLevel level;
}
