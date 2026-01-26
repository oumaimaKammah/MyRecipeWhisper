package com.myrecipewhisper.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserDTO(
        Integer userId,
        String username,
        String email,
        LocalDate birthday,
        LocalDateTime registeredAt) {
}
