package com.myrecipewhisper.backend.recipe.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myrecipewhisper.backend.recipe.dto.FavoriteRecipeDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeDTO;
import com.myrecipewhisper.backend.recipe.entity.FavoriteRecipe;
import com.myrecipewhisper.backend.recipe.mapper.FavoriteMapper;
import com.myrecipewhisper.backend.recipe.repository.FavoriteRecipeRepository;
import com.myrecipewhisper.backend.recipe.service.FavoriteRecipeService;
import com.myrecipewhisper.backend.user.entity.User;
import com.myrecipewhisper.backend.user.repository.UserRepository;

@Service
public class FavoriteRecipeServiceImpl implements FavoriteRecipeService {

    private final FavoriteMapper favoriteRecipeMapper;
    private final UserRepository userRepository;
    private final FavoriteRecipeRepository favoriteRecipeRepository;
    private final RecipeService recipeService;

    public FavoriteRecipeServiceImpl(UserRepository userRepository, FavoriteRecipeRepository favoriteRecipeRepository,
            FavoriteMapper favoriteRecipeMapper, RecipeService recipeService) {
        this.userRepository = userRepository;
        this.favoriteRecipeRepository = favoriteRecipeRepository;
        this.favoriteRecipeMapper = favoriteRecipeMapper;
        this.recipeService = recipeService;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void addFavorite(Integer recipeId) {
        var user = getCurrentUser();
        favoriteRecipeRepository.findByUserAndRecipeId(user, recipeId).ifPresent(favoriteRecipe -> {
            throw new RuntimeException("Already in favorites");
        });
        FavoriteRecipe favoriteRecipe = new FavoriteRecipe();
        favoriteRecipe.setUser(user);
        favoriteRecipe.setRecipeId(recipeId);
        favoriteRecipe.setAddedAt(LocalDateTime.now());
        favoriteRecipeRepository.save(favoriteRecipe);
    }

    @Override
    public void removeFavorite(Integer recipeId) {
        var user = getCurrentUser();
        var favoriteRecipe = favoriteRecipeRepository.findByUserAndRecipeId(user, recipeId)
                .orElseThrow(() -> new RuntimeException("Favorite recipe not found"));
        favoriteRecipeRepository.delete(favoriteRecipe);
    }

    @Override
    public List<FavoriteRecipeDTO> getFavorites() {
        var user = getCurrentUser();
        var favoriteRecipes = favoriteRecipeRepository.findAllByUserId(user.getId());
        return favoriteRecipes.stream().sorted(Comparator.comparing(FavoriteRecipe::getAddedAt).reversed()).map(fav -> {
            RecipeDTO recipeDTO = recipeService.getRecipeDetails(fav.getRecipeId());
            if (recipeDTO == null) {
                return favoriteRecipeMapper.toDTO(fav, null);
            }
            return favoriteRecipeMapper.toDTO(fav, recipeDTO);
        }).toList();
    }

}