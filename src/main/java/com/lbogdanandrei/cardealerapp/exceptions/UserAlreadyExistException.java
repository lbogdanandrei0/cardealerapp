package com.lbogdanandrei.cardealerapp.exceptions;

import javax.validation.constraints.NotBlank;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("User with email " + email + "already exists");
    }
}
