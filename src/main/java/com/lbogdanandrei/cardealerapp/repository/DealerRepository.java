package com.lbogdanandrei.cardealerapp.repository;

import com.lbogdanandrei.cardealerapp.model.DealerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface DealerRepository extends JpaRepository<DealerModel, BigInteger> {

    @Query(value = "select count(id) from dealer")
    int getNrOfRecords();

    Optional<DealerModel> findDealerByAddress(String address);
}
