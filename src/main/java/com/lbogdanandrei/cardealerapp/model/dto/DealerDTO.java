package com.lbogdanandrei.cardealerapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class DealerDTO implements Serializable {

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;
}
