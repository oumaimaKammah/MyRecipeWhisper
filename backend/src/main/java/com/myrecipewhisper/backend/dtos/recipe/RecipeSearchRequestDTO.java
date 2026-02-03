package com.myrecipewhisper.backend.dtos.recipe;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RecipeSearchRequestDTO(
        @NotEmpty List<String> ingredients,
        @NotNull Integer cuisineId) {

}
