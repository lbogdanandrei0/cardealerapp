package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.CarDTO;
import com.lbogdanandrei.cardealerapp.model.dto.DealerDTO;
import com.lbogdanandrei.cardealerapp.model.mapper.CarMapper;
import com.lbogdanandrei.cardealerapp.repository.CarRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<CarDTO> getCarsFromDealer(DealerModel dealer) {
        return  findCarByLocation(dealer.getId())
                .stream()
                .map(CarMapper.INSTANCE::carToCarDto)
                .collect(Collectors.toList());
    }

    public List<CarDTO> getAllCarsDTO(){
        return getAllCars()
                .stream()
                .map(CarMapper.INSTANCE::carToCarDto)
                .collect(Collectors.toList());
    }
}
