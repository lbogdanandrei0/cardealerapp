package com.lbogdanandrei.cardealerapp.repository;

import com.lbogdanandrei.cardealerapp.model.DealerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface DealerRepository extends JpaRepository<DealerModel, BigInteger> {

    @Query(value = "select count(id) from dealer")
    public BigInteger getNrOfRecords();
}
