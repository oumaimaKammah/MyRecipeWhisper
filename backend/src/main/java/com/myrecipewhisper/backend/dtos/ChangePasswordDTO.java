package com.myrecipewhisper.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ChangePasswordDTO(
                @NotBlank String oldPassword,
                @NotBlank @Size(min = 8, message = "New password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!?#%*&]).+$", message = "New password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character") String newPassword) {
}
