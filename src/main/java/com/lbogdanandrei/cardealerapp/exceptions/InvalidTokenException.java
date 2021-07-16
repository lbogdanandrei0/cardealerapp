package com.lbogdanandrei.cardealerapp.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String token) {
        super("Invalid token, activation failed");
    }
}
