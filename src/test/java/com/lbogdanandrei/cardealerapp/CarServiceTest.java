package com.lbogdanandrei.cardealerapp;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lbogdanandrei.cardealerapp.model.CarBrand;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.service.CarService;
import com.lbogdanandrei.cardealerapp.service.DealerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.EnumSet;
import java.util.List;

@SpringBootTest
public class CarServiceTest {

    @Autowired
    CarService carService;

    @MockBean
    DealerService dealerService;

    @BeforeEach
    public void init(){
        DealerModel dm1 = new DealerModel();
        dm1.setId(1);
        dm1.setAddress("Dealer 1 adress");
        dm1.setName("Dealer 1");
        dm1.setCreated_at(Timestamp.from(Instant.now()));
        DealerModel dm2 = new DealerModel();
        dm2.setId(2);
        dm2.setAddress("Dealer 2 adress");
        dm2.setName("Dealer 2");
        dm2.setCreated_at(Timestamp.from(Instant.now()));
        DealerModel dm3 = new DealerModel();
        dm3.setId(3);
        dm3.setAddress("Dealer 3 adress");
        dm3.setName("Dealer 3");
        dm3.setCreated_at(Timestamp.from(Instant.now()));
        Mockito.when(dealerService.getAllDealers()).thenReturn(List.of(dm1, dm2, dm3));
    }

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
                Assertions.assertEquals(cb, c.getBrand());
            }
        }
    }

    @Test
    public void findCarByLocation_shouldGetAllCarsFromCertainLocations(){
        List<DealerModel> dealers = dealerService.getAllDealers();
        dealers.stream().map((d) -> d.getId()).forEach(
                (id) -> {
                    carService.findCarByLocation(id).stream().forEach(
                            (car) -> {
                                Assertions.assertEquals(id, car.getLocation());
                            }
                    );
                }
        );
    }

    @Test
    public void findCarByEnginePower_shouldGetAllCarsWithEnginePowerSpecified(){
        List<CarModel> cars = carService.findCarWithEnginePowerBetween(100, 300);
        cars.stream().forEach(car -> Assertions.assertTrue(car.getEnginePower() >= 100 && car.getEnginePower() <= 300));
        cars = carService.findCarWithEnginePowerBetween(0, 600);
        cars.stream().forEach(car -> Assertions.assertTrue(car.getEnginePower() >= 0 && car.getEnginePower() <= 600));
        cars = carService.findCarWithEnginePowerBetween(Integer.MIN_VALUE, 220);
        cars.stream().forEach(car -> Assertions.assertTrue(car.getEnginePower() >= Integer.MIN_VALUE && car.getEnginePower() <= 220));
        cars = carService.findCarWithEnginePowerBetween(0, Integer.MAX_VALUE);
        cars.stream().forEach(car -> Assertions.assertTrue(car.getEnginePower() >= 0 && car.getEnginePower() <= Integer.MAX_VALUE));
        cars = carService.findCarWithEnginePowerBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
        Assertions.assertEquals(carService.getAllCars().size(), cars.size());
    }

    @Test
    public void findCarByEngineCapacity_shouldGetAllCarsWithEngineCapacitySpecified(){
        List<CarModel> cars = carService.findCarWithEngineCapacityBetween(800, 2200);
        cars.stream().forEach(car -> Assertions.assertTrue(car.getEngineCapacity() >= 800 && car.getEngineCapacity() <= 2200));
        cars = carService.findCarWithEngineCapacityBetween(2000, 5000);
        cars.stream().forEach(car -> Assertions.assertTrue(car.getEngineCapacity() >= 2000 && car.getEngineCapacity() <= 5000));
        cars = carService.findCarWithEngineCapacityBetween(Integer.MIN_VALUE, 2000);
        cars.stream().forEach(car -> Assertions.assertTrue(car.getEngineCapacity() >= Integer.MIN_VALUE && car.getEngineCapacity() <= 2000));
        cars = carService.findCarWithEngineCapacityBetween(2000, Integer.MAX_VALUE);
        cars.stream().forEach(car -> Assertions.assertTrue(car.getEngineCapacity() >= 2000 && car.getEngineCapacity() <= Integer.MAX_VALUE));
        cars = carService.findCarWithEngineCapacityBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
        Assertions.assertEquals(carService.getAllCars().size(), cars.size());
    }

    @Test
    public void findCarByIsNew_shouldGetAllNewCars(){
        List<CarModel> cars = carService.findAllNewCars();
        cars.stream().forEach(car -> Assertions.assertEquals(true, car.isNew()));
    }

    @Test
    public void findCarByIsNew_shouldGetAllShCars(){
        List<CarModel> cars = carService.findAllShCars();
        cars.stream().forEach(car -> Assertions.assertEquals(false, car.isNew()));
    }

    @Test
    public void findCarByHadAccident_shouldGetAllCarsWithoutCrash(){
        List<CarModel> cars = carService.findAllCarsWithoutCrash();
        cars.stream().forEach(car -> Assertions.assertEquals(false, car.isHadAccident()));
    }

    @Test
    public void findCarWithYearOfFabricationBetween_shouldGetAllCarsManufacturedInThatPeriod(){
        System.out.println(LocalDate.MIN.getYear() + " " + LocalDate.MAX.getYear());
        List<CarModel> cars = carService.findCarWithYearOfFabricationBetween(2010, 2021);
        cars.stream().forEach(car -> Assertions.assertTrue(
                car.getDateOfFabrication().toLocalDate().getYear() >= 2010 &&
                         car.getDateOfFabrication().toLocalDate().getYear() <= 2021));
        cars = carService.findCarWithYearOfFabricationBetween(LocalDate.MIN.getYear(), 2021);
        cars.stream().forEach(car -> Assertions.assertTrue(
                car.getDateOfFabrication().toLocalDate().getYear() >= LocalDate.MIN.getYear() &&
                        car.getDateOfFabrication().toLocalDate().getYear() <= 2021));
        cars = carService.findCarWithYearOfFabricationBetween(LocalDate.MIN.getYear(), LocalDate.MAX.getYear());
        Assertions.assertEquals(carService.getAllCars().size(), cars.size()); //TODO filter expected stream for null values
    }
}
