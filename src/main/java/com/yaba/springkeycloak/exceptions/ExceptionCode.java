package com.yaba.springkeycloak.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    CATEGORY_ALREADY_EXISTS(4000, "Cette categorie existe déjà"),
    CATEGORY_NOT_FOUND(4001, "Cette categorie n'existe pas"),


    BOOK_ALREADY_EXISTS(5000, "Ce livre existe déjà"),
    BOOK_NOT_FOUND(5001, "Ce livre n'existe pas"),


    NULL_VALUE_OF_ID(6000, "L'id ne doit pas être nul"),

    USER_ALREADY_EXISTS(7000, "Nom d'utilisateur ou adresse email déjà utilisé");

    private final int value;
    private final String message;
    ExceptionCode(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
