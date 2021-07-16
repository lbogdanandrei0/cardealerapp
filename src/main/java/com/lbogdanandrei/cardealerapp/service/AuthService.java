package com.lbogdanandrei.cardealerapp.service;

import com.lbogdanandrei.cardealerapp.exceptions.InvalidTokenException;
import com.lbogdanandrei.cardealerapp.exceptions.UserNotFoundException;
import com.lbogdanandrei.cardealerapp.model.TokenModel;
import com.lbogdanandrei.cardealerapp.model.UserModel;
import com.lbogdanandrei.cardealerapp.model.dto.AuthentificationResponse;
import com.lbogdanandrei.cardealerapp.model.dto.LoginRequestDTO;
import com.lbogdanandrei.cardealerapp.model.dto.RegisterRequestDTO;
import com.lbogdanandrei.cardealerapp.repository.TokenRepository;
import com.lbogdanandrei.cardealerapp.repository.UserRepository;
import com.lbogdanandrei.cardealerapp.security.JwtGenerator;
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
    private AuthenticationManager authentificationManager;

    @Autowired
    private JwtGenerator jwtGenerator;

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
        //userToken.setExpiryDate(); by default 10 min
        //TODO send token to user to activate account
        tokenRepository.save(userToken);
        return token;
    }

    public TokenModel getToken(String token){
        return tokenRepository.findTokenByToken(token).orElseThrow(() -> new InvalidTokenException(token));
    }

    public void activateUser(TokenModel requestToken){
        Optional<UserModel> user = userRepository.findUserByEmail(requestToken.getUser().getEmail());
        if(user.isPresent() && !user.get().isEnabled()){
            user.get().setEnabled(true);
            userRepository.save(user.get());
        }else{
            throw new UserNotFoundException(user.get());
        }
    }

    public AuthentificationResponse login(LoginRequestDTO requestBody) {
        Authentication auth = authentificationManager.authenticate(new UsernamePasswordAuthenticationToken(requestBody.getEmail(), requestBody.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtGenerator.generateToken(auth);
        return AuthentificationResponse.builder()
                .token(token)
                .email(requestBody.getEmail())
                .expiryAt(Timestamp.from(Instant.now().plusMillis(JwtGenerator.jwtExpirationInMillis)))
                .build();
    }
}
