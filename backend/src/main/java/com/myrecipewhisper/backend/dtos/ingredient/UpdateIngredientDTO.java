package com.myrecipewhisper.backend.dtos.ingredient;

import jakarta.validation.constraints.NotBlank;

public record UpdateIngredientDTO(

        @NotBlank(message = "Ingredient name is required") String name) {
}