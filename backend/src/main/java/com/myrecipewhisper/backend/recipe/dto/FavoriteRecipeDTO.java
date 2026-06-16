package com.myrecipewhisper.backend.recipe.dto;

import java.time.LocalDateTime;

public record FavoriteRecipeDTO(
        Integer recipeId,
        LocalDateTime addedAt,
        String title,
        String image,
        Integer readyInMinutes,
        Integer servings) {

}
