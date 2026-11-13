package com.example.noteapi.controller;

import com.example.noteapi.dto.LoginRequest;
import com.example.noteapi.dto.LoginResponse;
import com.example.noteapi.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/auth")
public class RestController {
    private final AuthenticationService authenticationService;

    public RestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authenticationService.authentication(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
