package com.lbogdanandrei.cardealerapp.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthentificationResponse {
    private String token;
    private String email;
    private java.sql.Timestamp expiryAt;
}
