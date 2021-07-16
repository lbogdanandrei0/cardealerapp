package com.lbogdanandrei.cardealerapp;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import com.lbogdanandrei.cardealerapp.repository.CarRepositoy;
import com.lbogdanandrei.cardealerapp.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class CarServiceIntegrationTest {

    @MockBean
    CarRepositoy carRepositoy;

    @Autowired
    CarService carService;

    @BeforeEach
    public void init(){
        CarModel model1 = new CarModel();
        model1.setLocation(5);
        model1.setBrand(CarBrand.Honda);
        model1.setId(1);
        model1.setEnginePower(200);
        model1.setEngineCapacity(1984);
        model1.setDateOfFabrication(Date.valueOf(LocalDate.of(2011, 05, 25)));
        CarModel model2 = new CarModel();
        model2.setLocation(6);
        model2.setBrand(CarBrand.Audi);
        model2.setId(2);
        model2.setEnginePower(170);
        model2.setEngineCapacity(2000);
        model2.setDateOfFabrication(Date.valueOf(LocalDate.of(2008, 07, 19)));
        CarModel model3 = new CarModel();
        model3.setLocation(5);
        model3.setBrand(CarBrand.Volkswagen);
        model3.setId(3);
        model3.setEnginePower(140);
        model3.setEngineCapacity(1600);
        model3.setDateOfFabrication(Date.valueOf(LocalDate.of(2021, 12, 12)));
        carRepositoy.save(model1);
        carRepositoy.save(model2);
        carRepositoy.save(model3);
        Mockito.when(carService.findCarWithYearOfFabricationBetween(2010, 2021)).thenReturn(List.of(model1, model3));
    }

    @Test
    public void findCarWithYearOfFabricationBetween_shouldGetTwoCars(){
        Assertions.assertTrue(carService.findCarWithYearOfFabricationBetween(2010, 2021).size() == 2);
    }
}
