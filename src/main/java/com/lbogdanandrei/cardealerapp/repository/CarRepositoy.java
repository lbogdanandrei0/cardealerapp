package com.lbogdanandrei.cardealerapp.repository;

import com.lbogdanandrei.cardealerapp.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import java.math.BigInteger;
import java.util.List;

public interface CarRepositoy extends JpaRepository<CarModel, BigInteger> {

    public List<CarModel> findCarByBrand(CarBrand brand);

    public CarModel findCarById(int id);
}
