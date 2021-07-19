package com.lbogdanandrei.cardealerapp.exceptions;

import com.lbogdanandrei.cardealerapp.model.UserModel;

import java.util.Optional;

public class UserWithInvalidTokenException extends RuntimeException {
    public UserWithInvalidTokenException(Optional<UserModel> toLogin) {
        super("Token expired for user with email " + toLogin.get().getEmail());
    }
}
