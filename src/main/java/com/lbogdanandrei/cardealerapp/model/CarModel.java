package com.lbogdanandrei.cardealerapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Entity(name = "car")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column
    @NonNull
    private BigInteger location;

    @Column
    @NonNull
    private CarBrand brand;

    @Column
    private CarType type;

    @Column
    private boolean is_new;

    @Column
    private boolean had_accident;

    @Column
    private short engine_capacity;

    @Column
    private short engine_power;

    @Column
    private java.sql.Date date_of_fabrication;

    @Column
    private float price;

    @Column
    @Lob
    private String description;
}
