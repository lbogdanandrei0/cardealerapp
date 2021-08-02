package com.lbogdanandrei.cardealerapp.model.task;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskDTO {

    @NotNull
    private String title;

    private String description;

    private TaskPriority priority;
}
