package com.lbogdanandrei.cardealerapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank(message = "email is mandatory")
    //@Email
    private String email;

    //TODO create custom validation for password
    @NotBlank(message = "password is mandatory")
    private String password;
}
