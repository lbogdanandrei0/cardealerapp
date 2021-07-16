package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.exceptions.InvalidTokenException;
import com.lbogdanandrei.cardealerapp.exceptions.UserNotFoundException;
import com.lbogdanandrei.cardealerapp.model.TokenModel;
import com.lbogdanandrei.cardealerapp.model.UserModel;
import com.lbogdanandrei.cardealerapp.model.dto.AuthenticationResponse;
import com.lbogdanandrei.cardealerapp.model.dto.LoginRequestDTO;
import com.lbogdanandrei.cardealerapp.model.dto.RefreshTokenRequestDTO;
import com.lbogdanandrei.cardealerapp.model.dto.RegisterRequestDTO;
import com.lbogdanandrei.cardealerapp.repository.TokenRepository;
import com.lbogdanandrei.cardealerapp.repository.UserRepository;
import com.lbogdanandrei.cardealerapp.security.JwtGenerator;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public void register(RegisterRequestDTO request){
        UserModel toRegister = new UserModel();
        toRegister.setEmail(request.getEmail());
        toRegister.setPassword(passwordEncoder.encode(request.getPassword()));
        toRegister.setEnabled(false);
        toRegister.setRole("USER");

        userRepository.save(toRegister);

        String token = generateToken(toRegister);
    }

    private String generateToken(UserModel toRegister) {
        String token = UUID.randomUUID().toString();
        TokenModel userToken = new TokenModel();
        userToken.setToken(token);
        userToken.setUser(toRegister);
        userToken.setExpiryDate(Timestamp.from(Instant.now().plusMillis(JwtGenerator.jwtExpirationInMillis)));
        //TODO send token to user to activate account
        tokenRepository.save(userToken);
        return token;
    }

    public void activateUserWithToken(String token){
        Optional<TokenModel> tokenModel = tokenRepository.findTokenByToken(token);
        if(!tokenModel.isPresent())
            throw new InvalidTokenException(token);
        if(tokenModel.get().getExpiryDate().before(Timestamp.from(Instant.now())))
            throw new InvalidTokenException(token, true);
        Optional<UserModel> user = userRepository.findUserByEmail(tokenModel.get().getUser().getEmail());
        if(user.isPresent() && !user.get().isEnabled()){
            user.get().setEnabled(true);
            userRepository.save(user.get());
        }else{
            throw new UserNotFoundException(user.get());
        }
    }

    public AuthenticationResponse login(LoginRequestDTO requestBody) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestBody.getEmail(), requestBody.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtGenerator.generateToken(auth);
        return AuthenticationResponse.builder()
                .token(token)
                .refreshToken(refreshTokenService.generateNewToken().getToken())
                .email(requestBody.getEmail())
                .expiryAt(Timestamp.from(Instant.now().plusMillis(JwtGenerator.jwtExpirationInMillis)))
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequestDTO requestBody){
        refreshTokenService.isValid(requestBody.getRefreshToken());
        String newToken = jwtGenerator.generateTokenWithUserName(requestBody.getEmail());
        return AuthenticationResponse.builder()
                .token(newToken)
                .refreshToken(refreshTokenService.generateNewToken().getToken())
                .expiryAt(Timestamp.from(Instant.now().plusMillis(JwtGenerator.jwtExpirationInMillis)))
                .email(requestBody.getEmail())
                .build();
    }

    public boolean isLoggedIn(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.isAuthenticated();
    }

    public ResponseEntity<String> logout(RefreshTokenRequestDTO requestBody){
        refreshTokenService.isValid(requestBody.getRefreshToken());
        refreshTokenService.deleteToken(requestBody.getRefreshToken());
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}
