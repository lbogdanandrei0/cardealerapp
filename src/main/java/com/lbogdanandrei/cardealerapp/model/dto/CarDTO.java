package com.lbogdanandrei.cardealerapp.model.dto;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
public class CarDTO implements Serializable {

    @NotEmpty
    private String location;

    @Enumerated(EnumType.STRING)
    private String brand;

    private boolean isNew;

    @Positive
    private int engineCapacity;

    @Positive
    private int enginePower;

    private java.sql.Date dateOfFabrication;

    @Positive
    private float price;

    private String vin;
}
