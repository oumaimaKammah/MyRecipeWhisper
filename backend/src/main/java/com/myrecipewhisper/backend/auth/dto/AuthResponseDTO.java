package com.myrecipewhisper.backend.auth.dto;

import com.myrecipewhisper.backend.user.dto.UserDTO;

public record AuthResponseDTO(
                String token,
                UserDTO user) {
}
