package com.myrecipewhisper.backend.dtos.ingredient;

import jakarta.validation.constraints.NotBlank;

public record CreateIngredientDTO(

                @NotBlank(message = "Ingredient name is required") String name) {
}
