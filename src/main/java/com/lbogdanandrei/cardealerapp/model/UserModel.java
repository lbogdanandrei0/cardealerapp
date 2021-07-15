package com.lbogdanandrei.cardealerapp.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "user_table")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", unique = true)
    @NotBlank(message = "email field can't be null")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "password field can't be null")
    private String password;

    @Column(name = "user_role")
    @NotBlank(message = "user role not defined")
    private String role;

    @Column(name = "enabled")
    private boolean enabled;
}
