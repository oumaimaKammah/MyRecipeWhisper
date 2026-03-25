package com.myrecipewhisper.backend.recipe.dto;

import java.util.List;

public record ExternalRecipeItemDTO(
                Integer id,
                String title,
                String image,
                Integer readyInMinutes,
                Integer servings,
                List<String> dishTypes,
                List<String> diets,
                List<String> occasions) {
}
