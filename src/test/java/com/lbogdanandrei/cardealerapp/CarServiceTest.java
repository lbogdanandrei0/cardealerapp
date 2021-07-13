package com.lbogdanandrei.cardealerapp;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import com.lbogdanandrei.cardealerapp.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.EnumSet;
import java.util.List;

@SpringBootTest
public class CarServiceTest {

    @Autowired
    CarService carService;

    @Test
    public void findAll_shouldGetAllCars(){
        List<CarModel> cars = carService.getAllCars();
        System.out.println(cars);
    }

    @Test
    public void findCarByBrand_shouldGetAllCarsByBrand(){
        for(CarBrand cb : CarBrand.values()) {
            List<CarModel> cars = carService.findCarByBrand(cb);
            for (CarModel c : cars) {
                Assertions.assertEquals(c.getBrand(), cb);
            }
        }
    }
}
