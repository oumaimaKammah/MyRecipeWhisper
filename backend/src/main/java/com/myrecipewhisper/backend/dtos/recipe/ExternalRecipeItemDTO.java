package com.myrecipewhisper.backend.dtos.recipe;

import java.util.List;

public record ExternalRecipeItemDTO(
                Integer id,
                String title,
                String image,
                Integer readyInMinutes,
                Integer servings,
                List<String> dishTypes,
                String sourceUrl) {

}
