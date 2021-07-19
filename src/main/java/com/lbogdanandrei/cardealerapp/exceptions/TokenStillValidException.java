package com.lbogdanandrei.cardealerapp.exceptions;

import javax.validation.constraints.NotBlank;

public class TokenStillValidException extends RuntimeException {
    public TokenStillValidException(@NotBlank(message = "provide a valid token") String token) {
        super("User's token is not expired yet. Can't generate new token");
    }
}
