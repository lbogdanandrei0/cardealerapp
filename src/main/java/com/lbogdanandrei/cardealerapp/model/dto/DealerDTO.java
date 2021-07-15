package com.lbogdanandrei.cardealerapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DealerDTO implements Serializable {
//TODO data validation
    private String name;

    private String address;
}
