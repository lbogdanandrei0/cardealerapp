package com.lbogdanandrei.cardealerapp.exceptions;

import com.lbogdanandrei.cardealerapp.model.UserModel;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UserModel userModel) {
        super("User with email " + userModel.getEmail() + " was not activated");
    }
}
