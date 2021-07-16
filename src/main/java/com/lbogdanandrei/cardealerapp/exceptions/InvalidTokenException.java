package com.lbogdanandrei.cardealerapp.exceptions;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String token, String msg){
        super(msg + " " + token);
    }
    public InvalidTokenException(String token, boolean expired){
        super("Token "+token+" is expired");
    }
    public InvalidTokenException(String token) {
        super("Invalid token, activation failed");
    }
}
