package com.lbogdanandrei.cardealerapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity(name = "token")
public class TokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token")
    @NotBlank(message = "provide a valid token")
    private String token;

    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private UserModel user;

    @Column(name = "expiry_date", columnDefinition = "default CURRENT_TIMESTAMP + (10 ||' minutes')::interval")
    @GeneratedValue
    private java.sql.Timestamp expiryDate;
}
