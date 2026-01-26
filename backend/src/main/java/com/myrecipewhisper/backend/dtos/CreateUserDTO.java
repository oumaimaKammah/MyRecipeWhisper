package com.myrecipewhisper.backend.dtos;

import java.time.LocalDate;

public record CreateUserDTO(
        String username,
        String email,
        String password,
        LocalDate birthday) {
}
