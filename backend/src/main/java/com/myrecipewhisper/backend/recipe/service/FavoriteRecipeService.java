package com.myrecipewhisper.backend.recipe.service;

import java.util.List;

import com.myrecipewhisper.backend.recipe.dto.FavoriteRecipeDTO;

public interface FavoriteRecipeService {
    void addFavorite(Integer recipeId);

    void removeFavorite(Integer recipeId);

    List<FavoriteRecipeDTO> getFavorites();

}
