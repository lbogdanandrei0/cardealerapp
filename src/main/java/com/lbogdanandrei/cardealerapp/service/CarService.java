package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import com.lbogdanandrei.cardealerapp.repository.CarRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<CarModel> findCarById(int id){
        return carRepositoy.findCarById(id);
    }

    public List<CarModel> findCarByLocation(int location){
        return carRepositoy.findCarByLocation(location);
    }

    public List<CarModel> findCarWithEngineCapacityBetween(int min, int max){
        return carRepositoy.findCarWithEngineCapacityBetween(min, max);
    }

    public List<CarModel> findCarWithEnginePowerBetween(int min, int max){
        return carRepositoy.findCarWithEnginePowerBetween(min, max);
    }

    public List<CarModel> findAllNewCars(){
        return carRepositoy.findCarByIsNew(true);
    }

    public List<CarModel> findAllShCars(){
        return carRepositoy.findCarByIsNew(false);
    }

    public List<CarModel> findAllCarsWithoutCrash(){
        return carRepositoy.findCarByHadAccident(false);
    }

    public List<CarModel> findCarWithYearOfFabricationBetween(int min, int max){
        return carRepositoy.findCarWithYearOfFabricationBetween(min, max);
    }
}
