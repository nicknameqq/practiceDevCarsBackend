package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.dto.auth.LoginRequest;
import com.practice.practiceDevBackend.dto.auth.LoginResponse;
import com.practice.practiceDevBackend.dto.auth.RegisterRequest;
import com.practice.practiceDevBackend.dto.auth.RegisterResponse;
import com.practice.practiceDevBackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") // эЗадаёт общий адрес для всех методов Controller.
@RequiredArgsConstructor //
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<RegisterResponse> createUser(@Valid @RequestBody RegisterRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
            ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
