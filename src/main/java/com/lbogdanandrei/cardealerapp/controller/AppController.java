package com.lbogdanandrei.cardealerapp.controller;

import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.CarDTO;
import com.lbogdanandrei.cardealerapp.model.dto.DealerDTO;
import com.lbogdanandrei.cardealerapp.service.CarService;
import com.lbogdanandrei.cardealerapp.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Object> getAllCarsFromDealer(@Valid @RequestBody DealerDTO dealer){
        DealerModel d = dealerService.findDealerByAddress(dealer.getAddress());
        return ResponseEntity.ok(carService.getCarsFromDealer(d));
    }

    @PostMapping("/dealers/new")
    public ResponseEntity<Object> saveNewDealer(@Valid @RequestBody DealerDTO dealer){
        DealerDTO response = dealerService.saveNewDealer(dealer);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cars/new")
    public ResponseEntity<Object> saveNewCar(@Valid @RequestBody CarDTO car){
        DealerModel dealer = dealerService.findDealerByAddress(car.getLocation());
        CarDTO response = carService.saveNewCar(car, dealer);
        return ResponseEntity.ok(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
