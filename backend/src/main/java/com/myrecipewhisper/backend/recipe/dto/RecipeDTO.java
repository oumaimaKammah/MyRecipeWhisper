package com.myrecipewhisper.backend.recipe.dto;

public record RecipeDTO(
                Integer id,
                String title,
                String image,
                Integer readyInMinutes,
                Integer servings,
                boolean isFavorite) {

}
