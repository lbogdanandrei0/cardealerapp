package com.lbogdanandrei.cardealerapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RefreshTokenRequestDTO {
    @NotBlank(message = "refresh token is mandatory")
    private String refreshToken;
    @NotBlank(message = "email is mandatory")
    private String email;
}
