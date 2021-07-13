package com.lbogdanandrei.cardealerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lbogdanandrei.cardealerapp.model.CarModel;
import java.math.BigInteger;

public interface CarRepositoy extends JpaRepository<CarModel, BigInteger> {
}
