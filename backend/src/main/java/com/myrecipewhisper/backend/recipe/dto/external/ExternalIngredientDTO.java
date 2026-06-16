package com.myrecipewhisper.backend.recipe.dto.external;

public record ExternalIngredientDTO(
        String name,
        Double amount,
        String unit,
        String original) {

}
