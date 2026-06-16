package com.myrecipewhisper.backend.auth.controller;

import com.myrecipewhisper.backend.auth.dto.AuthResponseDTO;
import com.myrecipewhisper.backend.auth.dto.LoginDTO;
import com.myrecipewhisper.backend.auth.dto.RegisterDTO;
import com.myrecipewhisper.backend.auth.dto.RegisterResponseDTO;
import com.myrecipewhisper.backend.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
