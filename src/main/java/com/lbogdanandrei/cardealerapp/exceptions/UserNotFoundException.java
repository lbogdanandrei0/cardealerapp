package com.lbogdanandrei.cardealerapp.exceptions;

import com.lbogdanandrei.cardealerapp.model.UserModel;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email, boolean alreadyActivated){
        super("User with email " + email + " was already activated");
    }
    public UserNotFoundException(String email) {
        super("User with email " + email + " was not found");
    }
}
