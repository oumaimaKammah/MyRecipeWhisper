package com.myrecipewhisper.backend.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank String username,

        @NotBlank @Email String email,

        @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!?#%*&]).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character") String password,

        @NotNull LocalDate birthday) {
}
