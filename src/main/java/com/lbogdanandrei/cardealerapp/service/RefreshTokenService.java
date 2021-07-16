package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.exceptions.InvalidTokenException;
import com.lbogdanandrei.cardealerapp.model.RefreshTokenModel;
import com.lbogdanandrei.cardealerapp.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenModel generateNewToken(){
        RefreshTokenModel newToken = new RefreshTokenModel();
        newToken.setToken(UUID.randomUUID().toString());
        newToken.setCreatedAt(Timestamp.from(Instant.now()));

        refreshTokenRepository.save(newToken);
        return newToken;
    }

    public void isValid(String token){
        refreshTokenRepository.findRefreshTokenByToken(token).orElseThrow(() -> new InvalidTokenException(token, "Invalid token:"));
    }

    @Transactional
    public void deleteToken(String token){
        refreshTokenRepository.deleteRefreshTokenByToken(token);
    }
}
