package com.example.finalprojetback.Controllers;

import com.example.finalprojetback.RequestsDto.AuthenticationResponse;
import com.example.finalprojetback.RequestsDto.LoginRequest;
import com.example.finalprojetback.RequestsDto.UserRequest;
import com.example.finalprojetback.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthCon {


    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRequest request
    ) {

        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}