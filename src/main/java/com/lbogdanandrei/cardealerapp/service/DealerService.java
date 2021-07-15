package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.exceptions.DealerAlreadyExistsException;
import com.lbogdanandrei.cardealerapp.exceptions.DealerNotFoundException;
import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.DealerDTO;
import com.lbogdanandrei.cardealerapp.model.mapper.DealerMapper;
import com.lbogdanandrei.cardealerapp.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealerService {

    @Autowired
    private DealerRepository dealerRepository;

    public List<DealerModel> getAllDealers() {
        return dealerRepository.findAll();
    }

    public int getNrOfRecords(){
        return dealerRepository.getNrOfRecords();
    }

    public DealerModel findDealerByAddress(String adr) throws DealerNotFoundException {
        return dealerRepository.findDealerByAddress(adr).orElseThrow(DealerNotFoundException::new);
    }

    public int getDealerIdFromAdress(String adr){
        return findDealerByAddress(adr).getId();
    }

    public List<DealerDTO> getAllDealersDTO(){
        return getAllDealers().stream()
                .map(DealerMapper.INSTANCE::dealerToDealerDto)
                .collect(Collectors.toList());
    }

    public DealerDTO saveNewDealer(DealerDTO dealer) throws DealerAlreadyExistsException {
        if(findDealerByAddress(dealer.getAddress()) != null)
            throw new DealerAlreadyExistsException();
        DealerModel toSave = DealerMapper.INSTANCE.dealerDtotoDealer(dealer);
        toSave.setCreated_at(Timestamp.from(Instant.now()));
        return DealerMapper.INSTANCE.dealerToDealerDto(dealerRepository.save(toSave));
    }
}
