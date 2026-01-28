package com.myrecipewhisper.backend.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(

        @NotBlank String username,
        @Email String email,
        @NotNull LocalDate birthday) {

}
