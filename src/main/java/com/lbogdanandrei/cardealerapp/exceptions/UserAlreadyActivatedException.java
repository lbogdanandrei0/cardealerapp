package com.lbogdanandrei.cardealerapp.exceptions;

public class UserAlreadyActivatedException extends RuntimeException {
    public UserAlreadyActivatedException(String email) {
        super("User with email " + email + " was already activated");
    }
}
