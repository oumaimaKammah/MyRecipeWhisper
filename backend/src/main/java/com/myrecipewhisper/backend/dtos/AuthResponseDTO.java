package com.myrecipewhisper.backend.dtos;

import com.myrecipewhisper.backend.dtos.user.UserDTO;

public record AuthResponseDTO(
                String token,
                UserDTO user) {
}
