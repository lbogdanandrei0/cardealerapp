package com.lbogdanandrei.cardealerapp.controller;

import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.CarDTO;
import com.lbogdanandrei.cardealerapp.model.dto.DealerDTO;
import com.lbogdanandrei.cardealerapp.service.CarService;
import com.lbogdanandrei.cardealerapp.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    DealerService dealerService;
    @Autowired
    CarService carService;

    @GetMapping("/dealers")
    public ResponseEntity<List<DealerDTO>> getAllDealers(){
        return ResponseEntity.ok(dealerService.getAllDealersDTO());
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars(){
        return ResponseEntity.ok(carService.getAllCarsDTO());
    }

    @GetMapping("/cars/from")
    public ResponseEntity<Object> getAllCarsFromDealer(@RequestBody DealerDTO dealer){
        DealerModel d = dealerService.findDealerByAddress(dealer.getAddress());
        return ResponseEntity.ok(carService.getCarsFromDealer(d));
    }

    @PostMapping("/dealers/new")
    public ResponseEntity<Object> saveNewDealer(@RequestBody DealerDTO dealer){
        DealerDTO response = dealerService.saveNewDealer(dealer);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cars/new")
    public ResponseEntity<Object> saveNewCar(@RequestBody CarDTO car){
        DealerModel dealer = dealerService.findDealerByAddress(car.getLocation());
        CarDTO response = carService.saveNewCar(car, dealer);
        return ResponseEntity.ok(response);
    }
}
