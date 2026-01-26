package com.myrecipewhisper.backend.dtos;

import java.time.LocalDate;

public record UpdateUserDTO(
        String username,
        String email,
        LocalDate birthday) {

}
