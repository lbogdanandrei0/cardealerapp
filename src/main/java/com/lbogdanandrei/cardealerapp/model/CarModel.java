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

    @Column(name = "location")
    @NonNull
    private int location;

    @Column(name = "brand")
    @NonNull
    @Enumerated(EnumType.STRING)
    private CarBrand brand;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CarType type;

    @Column(name = "is_new")
    private boolean isNew;

    @Column(name = "had_accident")
    private boolean hadAccident;

    @Column(name = "engine_capacity")
    private int engineCapacity;

    @Column(name = "engine_power")
    private int enginePower;

    @Column(name = "date_of_fabrication")
    private java.sql.Date dateOfFabrication;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "vin", unique = true)
    @NonNull
    private String vin;
}
