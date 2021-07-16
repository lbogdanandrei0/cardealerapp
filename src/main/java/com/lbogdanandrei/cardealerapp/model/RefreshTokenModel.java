package com.lbogdanandrei.cardealerapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "refresh_token")
public class RefreshTokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    @GeneratedValue
    private java.sql.Timestamp createdAt;
}
