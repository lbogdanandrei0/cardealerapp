package com.lbogdanandrei.cardealerapp.model.mapper;

import com.lbogdanandrei.cardealerapp.model.CarModel;
import com.lbogdanandrei.cardealerapp.model.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "location", target = "location"),
            @Mapping(source = "brand", target = "brand"),
            @Mapping(source = "_new", target = "_new"),
    })
    CarDTO carToCarDto(CarModel car);
}
