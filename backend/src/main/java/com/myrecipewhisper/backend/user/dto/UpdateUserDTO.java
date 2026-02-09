package com.myrecipewhisper.backend.user.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(

                @NotBlank(message = "Username cannot be empty") String username,
                @Email(message = "Email format is invalid") String email,
                @NotNull LocalDate birthday) {

}
