package com.myrecipewhisper.backend.dtos.recipe;

public record RecipeDTO(
                Integer id,
                String title,
                String image,
                Integer readyInMinutes,
                Integer servings) {

}
