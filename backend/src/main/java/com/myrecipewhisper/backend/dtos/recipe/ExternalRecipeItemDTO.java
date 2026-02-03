package com.myrecipewhisper.backend.dtos.recipe;

import java.util.List;

public record ExternalRecipeItemDTO(
        Integer id,
        String title,
        String image,
        Double spoonacularScore,
        List<String> dishTypes,
        String sourceUrl) {

}
