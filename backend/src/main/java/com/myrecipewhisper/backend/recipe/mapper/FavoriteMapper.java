package com.myrecipewhisper.backend.recipe.mapper;

import org.springframework.stereotype.Component;

import com.myrecipewhisper.backend.recipe.dto.FavoriteRecipeDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeDTO;
import com.myrecipewhisper.backend.recipe.entity.FavoriteRecipe;

@Component
public class FavoriteMapper {

    public FavoriteRecipeDTO toDTO(FavoriteRecipe favoriteRecipeEntity, RecipeDTO recipeDTO) {
        if (favoriteRecipeEntity == null) {
            return null;
        }
        return new FavoriteRecipeDTO(
                favoriteRecipeEntity.getRecipeId(),
                favoriteRecipeEntity.getAddedAt(),
                recipeDTO.title(),
                recipeDTO.image(),
                recipeDTO.readyInMinutes(),
                recipeDTO.servings());
    }
}
