package com.myrecipewhisper.backend.dtos.cuisine;

import jakarta.validation.constraints.NotBlank;

public record UpdateCuisineDTO(
        @NotBlank(message = "Name is required") String name) {

}
