package com.myrecipewhisper.backend.recipe.controller;

import com.myrecipewhisper.backend.recipe.dto.FavoriteRecipeDTO;
import com.myrecipewhisper.backend.recipe.service.FavoriteRecipeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteRecipeController {

    private final FavoriteRecipeService favoriteRecipeService;

    @PostMapping("/{recipeId}")
    public ResponseEntity<Void> addFavorite(@PathVariable Integer recipeId) {
        favoriteRecipeService.addFavorite(recipeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Integer recipeId) {
        favoriteRecipeService.removeFavorite(recipeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FavoriteRecipeDTO>> getFavorites() {
        List<FavoriteRecipeDTO> favorites = favoriteRecipeService.getFavorites();
        return ResponseEntity.ok(favorites);
    }
}
