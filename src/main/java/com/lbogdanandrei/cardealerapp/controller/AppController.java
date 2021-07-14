package com.lbogdanandrei.cardealerapp.controller;

import com.lbogdanandrei.cardealerapp.exceptions.DealerAlreadyExist;
import com.lbogdanandrei.cardealerapp.exceptions.DealerNotFoundException;
import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.CarDTO;
import com.lbogdanandrei.cardealerapp.model.dto.DealerDTO;
import com.lbogdanandrei.cardealerapp.service.CarService;
import com.lbogdanandrei.cardealerapp.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            DealerModel d = dealerService.findDealerByAddress(dealer.getAddress());
            if (d == null)
                throw new DealerNotFoundException();
            return ResponseEntity.ok(carService.getCarsFromDealer(d));
        }catch (DealerNotFoundException e){
            return new ResponseEntity<>("Dealer with address "+dealer.getAddress()+" was not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dealers/new")
    public ResponseEntity<Object> saveNewDealer(@RequestBody DealerDTO dealer){
        try{
            DealerDTO response = dealerService.saveNewDealer(dealer);
            return ResponseEntity.ok(response);
        }catch(DealerAlreadyExist e){
            return new ResponseEntity<>("Dealer with address " + dealer.getAddress() + " already exists", HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/cars/new")
    public ResponseEntity<Object> saveNewCar(@RequestBody CarDTO car){
        try {
            DealerModel dealer = dealerService.findDealerByAddress(car.getLocation());
            if(dealer == null)
                throw new DealerNotFoundException();
            CarDTO response = carService.saveNewCar(car, dealer);
            return ResponseEntity.ok(response);
        }catch (Exception e){
//            e.printStackTrace();
            return new ResponseEntity<>("Dealer address "+car.getLocation()+" not found", HttpStatus.NOT_FOUND);
        }
    }

}
