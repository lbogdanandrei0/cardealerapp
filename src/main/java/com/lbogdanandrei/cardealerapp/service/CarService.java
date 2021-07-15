package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.exceptions.CarAlreadyExistsException;
import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.CarDTO;
import com.lbogdanandrei.cardealerapp.model.mapper.CarMapper;
import com.lbogdanandrei.cardealerapp.repository.CarRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<CarDTO> toReturn = findCarByLocation(dealer.getId())
                .stream()
                .map(CarMapper.INSTANCE::carToCarDto)
                .collect(Collectors.toList());
        toReturn.forEach(car -> car.setLocation(dealer.getAddress()));
        return toReturn;
    }

    public List<CarDTO> getAllCarsDTO(){
        List<CarModel> cars = getAllCars();
        List<CarDTO> toReturn = new ArrayList<>();
        for(CarModel c:cars){
            CarDTO dto = CarMapper.INSTANCE.carToCarDto(c);
            dto.setLocation("dealer "+c.getLocation());
            toReturn.add(dto);
        }
        return toReturn;
    }

    public CarDTO saveNewCar(CarDTO car, DealerModel dealer) throws CarAlreadyExistsException {
        if(carRepositoy.findCarByVin(car.getVin()).isPresent())
            throw new CarAlreadyExistsException();
        CarModel toSave = CarMapper.INSTANCE.carDtoToCar(car);
        toSave.setLocation(dealer.getId());
        CarDTO response = CarMapper.INSTANCE.carToCarDto(carRepositoy.save(toSave));
        response.setLocation(dealer.getAddress());
        return response;
    }
}
