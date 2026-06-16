package com.myrecipewhisper.backend.recipe.dto;

public record RecipeIngredientDTO(
        String name,
        Double amount,
        String unit,
        String original) {

}
