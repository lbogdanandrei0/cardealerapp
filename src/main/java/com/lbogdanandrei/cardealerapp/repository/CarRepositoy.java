package com.lbogdanandrei.cardealerapp.repository;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CarRepositoy extends JpaRepository<CarModel, BigInteger> {

    List<CarModel> findCarByBrand(CarBrand brand);

    public Optional<CarModel> findCarById(int id);

    List<CarModel> findCarByLocation(int location);

    @Query("from car where engine_capacity >= :minimum and engine_capacity <= :maximum")
    public List<CarModel> findCarWithEngineCapacityBetween(@Param("minimum") int min,
                                                           @Param("maximum") int max);

    @Query("from car where engine_power >= :minimum and engine_power <= :maximum")
    public List<CarModel> findCarWithEnginePowerBetween(@Param("minimum")int min,
                                                        @Param("maximum") int max);

    @Query("from car where is_new = :isNew")
    public List<CarModel> findCarByIsNew(@Param("isNew") boolean isNew);

    @Query("from car where had_accident = :hadAccident")
    public List<CarModel> findCarByHadAccident(@Param("hadAccident") boolean hadAccident);

    @Query("from car where year(date_of_fabrication) >= :minYear and year(date_of_fabrication) <= :maxYear")
    public List<CarModel> findCarWithYearOfFabricationBetween(@Param("minYear") int min,
                                                              @Param("maxYear") int max);

    Optional<CarModel> findCarByVin(String vin);
}
