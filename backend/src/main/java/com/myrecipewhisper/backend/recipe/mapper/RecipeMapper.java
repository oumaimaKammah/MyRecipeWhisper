package com.myrecipewhisper.backend.recipe.mapper;

import org.springframework.stereotype.Component;

import com.myrecipewhisper.backend.recipe.dto.ExternalRecipeItemDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeDTO;

@Component
public class RecipeMapper {

    public RecipeDTO toDTO(ExternalRecipeItemDTO externalRecipeItemDTO) {
        if (externalRecipeItemDTO == null) {
            return null;
        }
        return new RecipeDTO(
                externalRecipeItemDTO.id(),
                externalRecipeItemDTO.title(),
                externalRecipeItemDTO.image(),
                externalRecipeItemDTO.readyInMinutes(),
                externalRecipeItemDTO.servings(),
                externalRecipeItemDTO.isFavorite());
    }

}
