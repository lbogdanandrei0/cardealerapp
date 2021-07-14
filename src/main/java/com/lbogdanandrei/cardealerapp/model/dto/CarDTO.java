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

    private int id;

    private int location;

    private String brand;

    private boolean is_new;

    private java.sql.Date date_of_fabrication;

    private float price;
}
