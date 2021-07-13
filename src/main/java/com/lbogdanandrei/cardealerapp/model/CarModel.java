package com.lbogdanandrei.cardealerapp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@ToString
@Entity(name = "car")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NonNull
    private BigInteger location;

    @Column
    @NonNull
    @Enumerated(EnumType.STRING)
    private CarBrand brand;

    @Column
    @Enumerated(EnumType.STRING)
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

    @Column(name = "description")
    private String description;
}
