package com.lbogdanandrei.cardealerapp.model.task;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    TaskModel taskDtoToTaskModel(TaskDTO task);

    TaskDTO taskModelToTaskDto(TaskModel task);
}
