package com.myrecipewhisper.backend.dtos.ingredient;

import jakarta.validation.constraints.NotBlank;

public record IngredientResponseDTO(

        @NotBlank(message = "Ingredient ID is required") Integer id,
        @NotBlank(message = "Ingredient name is required") String name) {
}