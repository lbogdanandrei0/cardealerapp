package com.lbogdanandrei.cardealerapp.controller;

import com.lbogdanandrei.cardealerapp.model.TokenModel;
import com.lbogdanandrei.cardealerapp.model.dto.RegisterRequestDTO;
import com.lbogdanandrei.cardealerapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO requestBody){
        authService.register(requestBody);
        return ResponseEntity.ok("User Registered");
    }

    @GetMapping("/activate/{token}")
    public ResponseEntity<String> activateUser(@PathVariable("token") String token){
        TokenModel tokenModel = authService.getToken(token);
        authService.activateUser(tokenModel);
        return ResponseEntity.ok("User activated");
    }

}
