package com.myrecipewhisper.backend.dtos.recipe;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record RecipeSearchRequestDTO(
                @NotEmpty List<String> ingredients,
                Integer cuisineId) {

}
