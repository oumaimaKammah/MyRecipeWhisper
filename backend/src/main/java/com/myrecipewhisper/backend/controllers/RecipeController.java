package com.myrecipewhisper.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myrecipewhisper.backend.dtos.recipe.RecipeDTO;
import com.myrecipewhisper.backend.dtos.recipe.RecipeSearchRequestDTO;
import com.myrecipewhisper.backend.services.impl.RecipeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping("/search")
    public ResponseEntity<List<RecipeDTO>> searchRecipes(@Valid @RequestBody RecipeSearchRequestDTO dto) {
        var recipes = recipeService.searchRecipes(dto);
        return ResponseEntity.ok(recipes);
    }

}
