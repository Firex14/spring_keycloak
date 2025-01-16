package com.yaba.springkeycloak.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    CATEGORY_ALREADY_EXISTS(4000, "Cette categorie existe déjà"),
    CATEGORY_NOT_FOUND(4001, "Cette categorie n'existe pas"),
    NULL_VALUE_OF_ID(5000, "L'id ne doit pas être nul");

    private final int value;
    private final String message;
    ExceptionCode(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
