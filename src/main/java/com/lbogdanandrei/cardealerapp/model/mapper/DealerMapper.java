package com.lbogdanandrei.cardealerapp.model.mapper;

import com.lbogdanandrei.cardealerapp.model.DealerModel;
import com.lbogdanandrei.cardealerapp.model.dto.DealerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DealerMapper {

    DealerMapper INSTANCE = Mappers.getMapper(DealerMapper.class);

    DealerDTO dealerToDealerDto(DealerModel dealer);

    DealerModel dealerDtotoDealer(DealerDTO dealer);
}
