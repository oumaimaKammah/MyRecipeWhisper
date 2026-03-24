package com.myrecipewhisper.backend.recipe.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.myrecipewhisper.backend.recipe.dto.ExternalRecipeItemDTO;
import com.myrecipewhisper.backend.recipe.dto.ExternalRecipeResponseDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeSearchRequestDTO;
import com.myrecipewhisper.backend.recipe.service.FavoriteRecipeService;
import com.myrecipewhisper.backend.recipe.service.external.ExternalRecipeClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final ExternalRecipeClient externalRecipeClient;
    private final FavoriteRecipeService favoriteRecipeService;

    public List<RecipeDTO> searchRecipes(RecipeSearchRequestDTO dto) {

        log.info("Searching recipes for ingredients = {} and cuisineId = {}", dto.ingredients(), dto.cuisineId());

        String cuisineName = dto.cuisineId() != null ? mapCuisineId(dto.cuisineId()) : null;

        ExternalRecipeResponseDTO externalResponse = externalRecipeClient.searchRecipes(dto.ingredients(), cuisineName);

        if (externalResponse == null || externalResponse.results() == null) {
            log.warn("No recipes found from external API");
            return List.of();
        }

        return externalResponse.results().stream()
                .map(r -> new ExternalRecipeItemDTO(
                        r.id(),
                        r.title(),
                        r.image(),
                        r.readyInMinutes(),
                        r.servings(),
                        r.dishTypes(),
                        r.sourceUrl(), false))
                .map(item -> new RecipeDTO(
                        item.id(),
                        item.title(),
                        item.image(),
                        item.readyInMinutes(),
                        item.servings(), false))
                .toList();
    }

    private String mapCuisineId(Integer id) {
        return switch (id) {
            case 1 -> "italian";
            case 2 -> "french";
            case 3 -> "mexican";
            case 4 -> "japanese";
            case 5 -> "indian";
            default -> null;
        };
    }

    @Cacheable("recipeDetails")
    public RecipeDTO getRecipeDetails(Integer recipeId) {

        RecipeDTO externalRecipe = externalRecipeClient.getRecipeDetails(recipeId);

        if (externalRecipe == null) {
            return null;
        }
        boolean isFavorite = favoriteRecipeService.isFavorite(recipeId);
        return new RecipeDTO(
                externalRecipe.id(),
                externalRecipe.title(),
                externalRecipe.image(),
                externalRecipe.readyInMinutes(),
                externalRecipe.servings(),
                isFavorite

        );
    }

}
