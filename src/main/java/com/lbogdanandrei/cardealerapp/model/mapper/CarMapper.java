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
            @Mapping(target = "location", ignore = true),
            @Mapping(source = "brand", target = "brand"),
            @Mapping(source = "_new", target = "_new"),
            @Mapping(source = "engine_power", target = "engine_power"),
            @Mapping(source = "engine_capacity", target = "engine_capacity"),
    })
    CarDTO carToCarDto(CarModel car);

    @Mappings({
            @Mapping(target = "location", ignore = true),
            @Mapping(source = "brand", target = "brand"),
            @Mapping(source = "_new", target = "_new"),
            @Mapping(source = "engine_power", target = "engine_power"),
            @Mapping(source = "engine_capacity", target = "engine_capacity"),
    })
    CarModel carDtoToCar(CarDTO car);
}
