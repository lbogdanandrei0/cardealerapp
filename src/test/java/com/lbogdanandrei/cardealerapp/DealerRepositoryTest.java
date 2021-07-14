package com.lbogdanandrei.cardealerapp;

import com.lbogdanandrei.cardealerapp.repository.DealerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DealerRepositoryTest {

    @Autowired
    private DealerRepository dealerRepository;

    @BeforeEach
    public void init() {
        System.out.println(dealerRepository != null);
    }

    @Test
    public void findAll_shouldGetAllRecords() {
        Assertions.assertEquals(dealerRepository.findAll().size(), dealerRepository.getNrOfRecords());
    }

    @Test
    public void findDealerByAddress_shouldGetDealerByAddress(){
        Assertions.assertEquals("adr1", dealerRepository.findDealerByAddress("adr1").getAddress());
        Assertions.assertEquals("adr2", dealerRepository.findDealerByAddress("adr2").getAddress());
    }

}
