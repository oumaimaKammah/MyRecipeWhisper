package com.myrecipewhisper.backend.recipe.dto;

import java.util.List;

public record RecipeDTO(
        Integer id,
        String title,
        String image,
        Integer readyInMinutes,
        Integer servings,
        boolean isFavorite,
        List<String> tags) {

}
