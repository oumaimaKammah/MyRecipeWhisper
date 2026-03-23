package com.myrecipewhisper.backend.recipe.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record RecipeSearchRequestDTO(
        @NotEmpty List<String> ingredients,
        Integer cuisineId) {

}
