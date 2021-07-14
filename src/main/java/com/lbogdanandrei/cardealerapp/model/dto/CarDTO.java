package com.lbogdanandrei.cardealerapp.model.dto;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarType;
import org.springframework.lang.NonNull;

import javax.persistence.*;

public class CarDTO {

    private int id;

    private int location;

    private CarBrand brand;

    private boolean is_new;

    private java.sql.Date date_of_fabrication;

    private float price;
}
