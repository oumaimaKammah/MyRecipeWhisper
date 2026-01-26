package com.myrecipewhisper.backend.dtos;

public record ChangePasswordDTO(
        String oldPassword,
        String newPassword) {
}
