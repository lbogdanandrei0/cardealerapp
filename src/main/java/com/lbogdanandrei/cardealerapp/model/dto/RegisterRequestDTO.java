package com.lbogdanandrei.cardealerapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
