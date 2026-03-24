package com.myrecipewhisper.backend.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myrecipewhisper.backend.recipe.entity.FavoriteRecipe;
import com.myrecipewhisper.backend.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Integer> {

    List<FavoriteRecipe> findAllByUserId(Integer userId);

    Optional<FavoriteRecipe> findByUserAndRecipeId(User user, Integer recipeId);

    void deleteByUserAndRecipeId(User user, Integer recipeId);
}
