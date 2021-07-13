package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import com.lbogdanandrei.cardealerapp.repository.CarRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepositoy carRepositoy;

    public List<CarModel> getAllCars(){
        return carRepositoy.findAll();
    }

    public List<CarModel> findCarByBrand(CarBrand brand){
        return carRepositoy.findCarByBrand(brand);
    }

    public CarModel findCarById(int id){
        return carRepositoy.findCarById(id);
    }
}
