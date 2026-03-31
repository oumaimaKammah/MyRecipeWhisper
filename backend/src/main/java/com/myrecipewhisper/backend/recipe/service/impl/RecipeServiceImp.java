package com.myrecipewhisper.backend.recipe.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.myrecipewhisper.backend.recipe.dto.ExternalRecipeResponseDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeSearchRequestDTO;
import com.myrecipewhisper.backend.recipe.dto.external.ExternalRecipeItemDTO;
import com.myrecipewhisper.backend.recipe.mapper.RecipeMapper;
import com.myrecipewhisper.backend.recipe.service.FavoriteRecipeService;
import com.myrecipewhisper.backend.recipe.service.external.ExternalRecipeClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImp {

    private final ExternalRecipeClient externalRecipeClient;
    private final FavoriteRecipeService favoriteRecipeService;
    private final RecipeMapper recipeMapper;

    public List<RecipeDTO> searchRecipes(RecipeSearchRequestDTO dto) {

        log.info("Searching recipes for ingredients = {} and cuisineId = {}", dto.ingredients(), dto.cuisineId());

        String cuisineName = dto.cuisineId() != null ? mapCuisineId(dto.cuisineId()) : null;
        String tags = dto.tags() == null ? "" : String.join(",", dto.tags());

        ExternalRecipeResponseDTO externalResponse = externalRecipeClient.searchRecipes(dto.ingredients(), cuisineName,
                tags);

        if (externalResponse == null || externalResponse.results() == null) {
            log.warn("No recipes found from external API");
            return List.of();
        }

        return externalResponse.results().stream()
                .map(item -> {
                    RecipeDTO base = recipeMapper.toDTO(item);
                    ExternalRecipeItemDTO details = externalRecipeClient.getRecipeDetails(item.id());
                    RecipeDTO full = recipeMapper.toDTO(details);
                    return new RecipeDTO(
                            base.id(),
                            base.title(),
                            base.image(),
                            base.readyInMinutes(),
                            base.servings(),
                            base.isFavorite(),
                            base.tags(),
                            full.ingredients());
                })
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

        var externalRecipe = externalRecipeClient.getRecipeDetails(recipeId);

        if (externalRecipe == null) {
            return null;
        }
        boolean isFavorite = favoriteRecipeService.isFavorite(recipeId);
        RecipeDTO recipeDTO = recipeMapper.toDTO(externalRecipe);
        recipeDTO = new RecipeDTO(
                recipeDTO.id(),
                recipeDTO.title(),
                recipeDTO.image(),
                recipeDTO.readyInMinutes(),
                recipeDTO.servings(),
                isFavorite,
                recipeDTO.tags(), recipeDTO.ingredients());
        return recipeDTO;
    }

}
