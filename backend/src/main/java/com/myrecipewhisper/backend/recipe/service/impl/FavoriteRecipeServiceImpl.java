package com.myrecipewhisper.backend.recipe.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myrecipewhisper.backend.recipe.dto.FavoriteRecipeDTO;
import com.myrecipewhisper.backend.recipe.dto.RecipeDTO;
import com.myrecipewhisper.backend.recipe.entity.FavoriteRecipe;
import com.myrecipewhisper.backend.recipe.mapper.RecipeMapper;
import com.myrecipewhisper.backend.recipe.repository.FavoriteRecipeRepository;
import com.myrecipewhisper.backend.recipe.service.FavoriteRecipeService;
import com.myrecipewhisper.backend.recipe.service.external.ExternalRecipeClient;
import com.myrecipewhisper.backend.user.entity.User;
import com.myrecipewhisper.backend.user.repository.UserRepository;

@Service
public class FavoriteRecipeServiceImpl implements FavoriteRecipeService {

    private final RecipeMapper recipeMapper;
    private final UserRepository userRepository;
    private final FavoriteRecipeRepository favoriteRecipeRepository;
    private final ExternalRecipeClient externalRecipeClient;

    public FavoriteRecipeServiceImpl(
            UserRepository userRepository,
            FavoriteRecipeRepository favoriteRecipeRepository,
            RecipeMapper recipeMapper,
            ExternalRecipeClient externalRecipeClient) {

        this.userRepository = userRepository;
        this.favoriteRecipeRepository = favoriteRecipeRepository;
        this.recipeMapper = recipeMapper;
        this.externalRecipeClient = externalRecipeClient;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void addFavorite(Integer recipeId) {
        User user = getCurrentUser();

        if (favoriteRecipeRepository.existsByUserIdAndRecipeId(user.getId(), recipeId)) {
            throw new RuntimeException("Already in favorites");
        }

        FavoriteRecipe favoriteRecipe = new FavoriteRecipe();
        favoriteRecipe.setUser(user);
        favoriteRecipe.setRecipeId(recipeId);
        favoriteRecipe.setAddedAt(LocalDateTime.now());

        favoriteRecipeRepository.save(favoriteRecipe);
    }

    @Override
    public void removeFavorite(Integer recipeId) {
        User user = getCurrentUser();

        FavoriteRecipe favorite = favoriteRecipeRepository
                .findByUserAndRecipeId(user, recipeId)
                .orElseThrow(() -> new RuntimeException("Favorite recipe not found"));

        favoriteRecipeRepository.delete(favorite);
    }

    @Override
    public boolean isFavorite(Integer recipeId) {
        User user = getCurrentUser();
        return favoriteRecipeRepository.existsByUserIdAndRecipeId(user.getId(), recipeId);
    }

    @Override
    public List<FavoriteRecipeDTO> getFavorites() {
        User user = getCurrentUser();

        return favoriteRecipeRepository.findAllByUserId(user.getId())
                .stream()
                .map(fav -> {
                    var external = externalRecipeClient.getRecipeDetails(fav.getRecipeId());
                    if (external == null)
                        return null;
                    RecipeDTO recipe = recipeMapper.toDTO(external);
                    recipe = new RecipeDTO(
                            recipe.id(),
                            recipe.title(),
                            recipe.image(),
                            recipe.readyInMinutes(),
                            recipe.servings(),
                            true,
                            recipe.tags(), recipe.ingredients());

                    return new FavoriteRecipeDTO(
                            recipe.id(),
                            fav.getAddedAt(),
                            recipe.title(),
                            recipe.image(),
                            recipe.readyInMinutes(),
                            recipe.servings()

                );
                })
                .filter(dto -> dto != null)
                .sorted(Comparator.comparing(FavoriteRecipeDTO::addedAt).reversed())
                .toList();
    }
}
