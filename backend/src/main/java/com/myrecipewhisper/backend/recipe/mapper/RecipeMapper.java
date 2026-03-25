package com.myrecipewhisper.backend.recipe.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.myrecipewhisper.backend.recipe.dto.ExternalRecipeItemDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeDTO;

@Component
public class RecipeMapper {

    public RecipeDTO toDTO(ExternalRecipeItemDTO externalRecipeItemDTO) {
        if (externalRecipeItemDTO == null) {
            return null;
        }
        List<String> tags = new ArrayList<>();
        if (externalRecipeItemDTO.dishTypes() != null)
            tags.addAll(externalRecipeItemDTO.dishTypes());
        if (externalRecipeItemDTO.diets() != null)
            tags.addAll(externalRecipeItemDTO.diets());
        if (externalRecipeItemDTO.occasions() != null)
            tags.addAll(externalRecipeItemDTO.occasions());
        return new RecipeDTO(
                externalRecipeItemDTO.id(),
                externalRecipeItemDTO.title(),
                externalRecipeItemDTO.image(),
                externalRecipeItemDTO.readyInMinutes(),
                externalRecipeItemDTO.servings(),
                false,
                tags);
        }

}
