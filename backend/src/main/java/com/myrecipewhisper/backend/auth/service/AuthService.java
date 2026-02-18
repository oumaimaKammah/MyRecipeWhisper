package com.myrecipewhisper.backend.auth.service;

import com.myrecipewhisper.backend.auth.dto.AuthResponseDTO;
import com.myrecipewhisper.backend.auth.dto.LoginDTO;
import com.myrecipewhisper.backend.auth.dto.RegisterDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterDTO registerDTO);

    AuthResponseDTO login(LoginDTO loginDTO);

}
