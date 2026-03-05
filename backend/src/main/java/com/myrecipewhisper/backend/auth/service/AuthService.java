package com.myrecipewhisper.backend.auth.service;

import com.myrecipewhisper.backend.auth.dto.AuthResponseDTO;
import com.myrecipewhisper.backend.auth.dto.LoginDTO;
import com.myrecipewhisper.backend.auth.dto.RegisterDTO;
import com.myrecipewhisper.backend.auth.dto.RegisterResponseDTO;

public interface AuthService {

    RegisterResponseDTO register(RegisterDTO registerDTO);

    AuthResponseDTO login(LoginDTO loginDTO);

}
