package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class DealerService {

    @Autowired
    private DealerRepository dealerRepository;

    public List<DealerModel> getAllDealers() {
        return dealerRepository.findAll();
    }

    public BigInteger getNrOfRecords(){
        return dealerRepository.getNrOfRecords();
    }

}
