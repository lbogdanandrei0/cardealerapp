package com.lbogdanandrei.cardealerapp.controller;

import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.CarDTO;
import com.lbogdanandrei.cardealerapp.model.dto.DealerDTO;
import com.lbogdanandrei.cardealerapp.service.AuthService;
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
@RequestMapping("/api/intern")
public class AppController {

    @Autowired
    private DealerService dealerService;
    @Autowired
    private CarService carService;
    @Autowired
    private AuthService authService;

    @GetMapping("/dealers")
    public ResponseEntity<List<DealerDTO>> getAllDealers(){
        System.out.println("Logged in: " + authService.isLoggedIn());
        return ResponseEntity.ok(dealerService.getAllDealersDTO());
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars(){
        System.out.println("Logged in: " + authService.isLoggedIn());
        return ResponseEntity.ok(carService.getAllCarsDTO());
    }

    @GetMapping("/cars/from")
    public ResponseEntity<Object> getAllCarsFromDealer(@Valid @RequestBody DealerDTO dealer){
        DealerModel d = dealerService.findDealerByAddress(dealer.getAddress());
        return ResponseEntity.ok(carService.getCarsFromDealer(d));
    }

    @PostMapping("/dealers/new")
    public ResponseEntity<Object> saveNewDealer(@Valid @RequestBody DealerDTO dealer){
        System.out.println("Logged in: " + authService.isLoggedIn());
        DealerDTO response = dealerService.saveNewDealer(dealer);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cars/new")
    public ResponseEntity<Object> saveNewCar(@Valid @RequestBody CarDTO car){
        System.out.println("Logged in: " + authService.isLoggedIn());
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
