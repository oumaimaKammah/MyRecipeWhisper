package com.myrecipewhisper.backend.dtos.cuisine;

import jakarta.validation.constraints.NotNull;

public record SelectCuisineDTO(
        @NotNull(message = "Cuisine ID is required") Integer cuisineId) {
}
