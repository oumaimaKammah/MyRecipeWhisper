package com.myrecipewhisper.backend.mappers;

import org.springframework.stereotype.Component;

import com.myrecipewhisper.backend.dtos.recipe.ExternalRecipeItemDTO;
import com.myrecipewhisper.backend.dtos.recipe.RecipeDTO;

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
                externalRecipeItemDTO.servings());
    }

}
