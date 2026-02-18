package com.myrecipewhisper.backend.auth.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.myrecipewhisper.backend.auth.dto.AuthResponseDTO;
import com.myrecipewhisper.backend.auth.dto.LoginDTO;
import com.myrecipewhisper.backend.auth.dto.RegisterDTO;
import com.myrecipewhisper.backend.auth.service.AuthService;
import com.myrecipewhisper.backend.user.dto.CreateUserDTO;
import com.myrecipewhisper.backend.user.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterDTO request) {
        log.info("Registering user with email: {}", request.email());
        var createUserDTO = toCreateUserDTO(request);
        userService.createUser(createUserDTO);
        log.info("User registered successfully with email: {}", request.email());
        return new AuthResponseDTO("User registered successfully", null);
    }

    private CreateUserDTO toCreateUserDTO(RegisterDTO registerDTO) {
        return new CreateUserDTO(
                registerDTO.username(),
                registerDTO.email(),
                registerDTO.password(),
                registerDTO.birthday());
    }

    @Override
    public AuthResponseDTO login(LoginDTO request) {
        log.info("Attempting login for email: {}", request.email());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()));
        log.info("Login successful for email: {}", request.email());
        return new AuthResponseDTO("Login successful", null);
    }

}
