package com.myrecipewhisper.backend.user.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
                @NotBlank(message = "Username cannot be empty") String username,

                @NotBlank(message = "Email cannot be empty") @Email(message = "Email format is invalid") String email,

                @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!?#%*&]).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character") String password,

                @NotNull LocalDate birthday) {
}
