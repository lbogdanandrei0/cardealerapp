package com.lbogdanandrei.cardealerapp.model.dto;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
public class CarDTO implements Serializable {
    private String location;

    private String brand;

    private boolean isNew;

    private int engineCapacity;

    private int enginePower;

    private java.sql.Date dateOfFabrication;

    private float price;

    private String vin;
}
