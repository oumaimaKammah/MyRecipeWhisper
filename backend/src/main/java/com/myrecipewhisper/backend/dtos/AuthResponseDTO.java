package com.myrecipewhisper.backend.dtos;

public record AuthResponseDTO(
        String token,
        UserDTO user) {
}
