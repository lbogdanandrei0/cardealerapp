package com.lbogdanandrei.cardealerapp;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.DealerDTO;
import com.lbogdanandrei.cardealerapp.model.mapper.DealerMapper;
import com.lbogdanandrei.cardealerapp.service.DealerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@SpringBootTest
public class DealerServiceTest {

    @Autowired
    private DealerService dealerService;

    @Test
    public void mapperTest(){
        DealerModel d = new DealerModel();
        d.setId(1);
        d.setName("Test Dealer");
        d.setAddress("Test Dealer Address");
        d.setCreated_at(Timestamp.from(Instant.now()));
        DealerDTO dto = DealerMapper.INSTANCE.dealerToDealerDto(d);
        Assertions.assertEquals(d.getAddress(), dto.getAddress());
        Assertions.assertEquals(d.getName(), dto.getName());
    }

    @Test
    public void getAllDealerDTO_shouldGetAllDealerDTO(){
        List<DealerModel> dealers = dealerService.getAllDealers();
        List<DealerDTO> dealersDTO = dealerService.getAllDealersDTO();
        Assertions.assertEquals(dealers.size(), dealersDTO.size());
    }

}
