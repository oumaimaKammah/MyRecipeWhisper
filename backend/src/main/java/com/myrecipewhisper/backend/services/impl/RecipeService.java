package com.myrecipewhisper.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myrecipewhisper.backend.dtos.recipe.RecipeDTO;
import com.myrecipewhisper.backend.dtos.recipe.RecipeSearchRequestDTO;
import com.myrecipewhisper.backend.mappers.RecipeMapper;
import com.myrecipewhisper.backend.services.external.ExternalRecipeClient;
import com.myrecipewhisper.backend.validators.CuisineValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeService {
    private final CuisineValidator cuisineValidator;
    private final ExternalRecipeClient externalRecipeClient;
    private final RecipeMapper recipeMapper;

    public RecipeService(CuisineValidator cuisineValidator,
            ExternalRecipeClient externalRecipeClient,
            RecipeMapper recipeMapper) {
        this.cuisineValidator = cuisineValidator;
        this.externalRecipeClient = externalRecipeClient;
        this.recipeMapper = recipeMapper;
    }

    public List<RecipeDTO> searchRecipes(RecipeSearchRequestDTO dto) {
        log.info("Searching recipes for ingredients = {} and cuisineId = {}", dto.ingredients(), dto.cuisineId());
        var cuisine = cuisineValidator.validateCuisineExists(dto.cuisineId());
        String ingredients = String.join(",", dto.ingredients());
        var externalResponse = externalRecipeClient.searchRecipes(ingredients, cuisine.getCuisineName());
        if (externalResponse == null || externalResponse.results() == null) {
            log.warn("No recipes found from external API");
            return List.of();

        }
        var recipes = externalResponse.results().stream()
                .map(recipeMapper::toDTO)
                .toList();
        log.info("Found {} recipes from external API", recipes.size());
        return recipes;

    }

}
