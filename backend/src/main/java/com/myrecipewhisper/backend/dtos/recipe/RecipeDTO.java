package com.myrecipewhisper.backend.dtos.recipe;

import java.util.List;

public record RecipeDTO(
        String title,
        String image,
        Double rating,
        List<String> tags,
        String sourceUrl) {

}
