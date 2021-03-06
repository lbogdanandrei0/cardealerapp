package com.lbogdanandrei.cardealerapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "dealer")
public class DealerModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(unique = true)
    @NonNull
    private String address;

    @Column(name = "created_at", columnDefinition = "default CURRENT_TIMESTAMP")
    private java.sql.Timestamp created_at;
}
