package com.example.noteapi.service;

import com.example.noteapi.auth.JwtService;
import com.example.noteapi.dto.LoginRequest;
import com.example.noteapi.dto.LoginResponse;
import com.example.noteapi.exception.LoginArgumentNotValidationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // ログイン認証
    public LoginResponse authentication(LoginRequest loginRequest) {

        // ユーザーIDが空の場合
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isBlank()) {
            throw new LoginArgumentNotValidationException("username is error");
        }

        // パスワードが空の場合
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {
            throw new LoginArgumentNotValidationException("password is error");
        }

        // 認証
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // JWTトークンを生成
        String token = jwtService.generateToken(loginRequest.getUsername());

        // レスポンスを作成
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);

        return loginResponse;
    }
}