package com.lbogdanandrei.cardealerapp.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
    private String refreshToken;
    private String email;
    private java.sql.Timestamp expiryAt;
}
