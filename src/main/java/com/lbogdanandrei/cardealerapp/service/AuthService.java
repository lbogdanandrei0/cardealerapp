package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.exceptions.*;
import com.lbogdanandrei.cardealerapp.model.TokenModel;
import com.lbogdanandrei.cardealerapp.model.UserModel;
import com.lbogdanandrei.cardealerapp.model.dto.AuthenticationResponse;
import com.lbogdanandrei.cardealerapp.model.dto.LoginRequestDTO;
import com.lbogdanandrei.cardealerapp.model.dto.RefreshTokenRequestDTO;
import com.lbogdanandrei.cardealerapp.model.dto.RegisterRequestDTO;
import com.lbogdanandrei.cardealerapp.repository.TokenRepository;
import com.lbogdanandrei.cardealerapp.repository.UserRepository;
import com.lbogdanandrei.cardealerapp.security.JwtGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
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

    @Autowired
    private EmailService emailService;

    @Transactional
    public void register(RegisterRequestDTO request){
        UserModel toRegister = new UserModel();
        toRegister.setEmail(request.getEmail());
        toRegister.setPassword(passwordEncoder.encode(request.getPassword()));
        toRegister.setEnabled(false);
        toRegister.setRole("USER");
        try {
            userRepository.save(toRegister);

            String token = generateToken(toRegister);
            //TODO send token to user to activate account

            String mailContent = "Access http://localhost:8080/api/auth/activate/" + token + " to activate the account";
            emailService.send(toRegister.getEmail(), "Account Activation", mailContent);
        }catch(Exception e){
            throw new UserAlreadyExistException(request.getEmail());
        }
    }

    private String generateToken(UserModel toRegister) {
        String token = UUID.randomUUID().toString();
        TokenModel userToken = new TokenModel();
        userToken.setToken(token);
        userToken.setUser(toRegister);
        userToken.setExpiryDate(Timestamp.from(Instant.now().plusMillis(JwtGenerator.jwtExpirationInMillis)));
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
            throw new UserNotFoundException(user.get().getEmail(), true);
        }
    }

    public AuthenticationResponse login(LoginRequestDTO requestBody) {
        Optional<UserModel> toLogin = userRepository.findUserByEmail(requestBody.getEmail());
        if(toLogin.isPresent() && !toLogin.get().isEnabled())
            throw new UserWithInvalidTokenException(toLogin);
        else if(!toLogin.isPresent())
            throw new UserNotFoundException(requestBody.getEmail());
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
        return auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
    }

    public ResponseEntity<String> logout(RefreshTokenRequestDTO requestBody){
        refreshTokenService.isValid(requestBody.getRefreshToken());
        refreshTokenService.deleteToken(requestBody.getRefreshToken());
        Authentication auth = new AnonymousAuthenticationToken("Anonymous user", "Anonymous user", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> resetToken(String email) {
        Optional<UserModel> user = userRepository.findUserByEmail(email);
        if(!user.isPresent())
            throw new UserNotFoundException(email);
        else if(user.get().isEnabled())
            throw new UserAlreadyActivatedException(email);
        Optional<TokenModel> token = tokenRepository.findTokenByEmail(email);
        if(token.isPresent() && token.get().getExpiryDate().after(Timestamp.from(Instant.now())))
            throw new TokenStillValidException(token.get().getToken());
        token.get().setToken(UUID.randomUUID().toString());
        token.get().setExpiryDate(Timestamp.from(Instant.now().plusMillis(JwtGenerator.jwtExpirationInMillis)));
        tokenRepository.save(token.get());
        String mailContent = "Access http://localhost:8080/api/auth/activate/" + token.get().getToken() + " to activate you account";
        emailService.send(email, "New Activation Token", mailContent);
        return ResponseEntity.ok("Another token was generated for user " + email);
    }
}
