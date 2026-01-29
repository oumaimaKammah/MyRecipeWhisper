package com.myrecipewhisper.backend.controllers;

import com.myrecipewhisper.backend.api.ResponseFactory;
import com.myrecipewhisper.backend.dtos.ingredient.CreateIngredientDTO;
import com.myrecipewhisper.backend.dtos.ingredient.IngredientResponseDTO;
import com.myrecipewhisper.backend.dtos.ingredient.UpdateIngredientDTO;
import com.myrecipewhisper.backend.services.impl.IngredientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@Slf4j
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public Object create(@Valid @RequestBody CreateIngredientDTO dto) {
        log.info("API CALL: POST /api/ingredients — Creating ingredient '{}'", dto.name());
        var response = ingredientService.create(dto);
        log.info("API RESPONSE: Ingredient created with ID {}", response.id());
        return ResponseFactory.created(response);
    }

    @GetMapping
    public Object getAll() {
        log.info("API CALL: GET /api/ingredients — Fetching all ingredients");
        List<IngredientResponseDTO> response = ingredientService.getAll();
        log.info("API RESPONSE: {} ingredients returned", response.size());
        return ResponseFactory.ok(response);
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Integer id) {
        log.info("API CALL: GET /api/ingredients/{} — Fetching ingredient by ID", id);
        var response = ingredientService.gIngredientResponseDTOetById(id);
        log.info("API RESPONSE: Ingredient {} returned", id);
        return ResponseFactory.ok(response);
    }

    @PutMapping("/{id}")
    public Object update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateIngredientDTO dto) {
        log.info("API CALL: PUT /api/ingredients/{} — Updating ingredient to '{}'", id, dto.name());
        var response = ingredientService.update(id, dto);
        log.info("API RESPONSE: Ingredient {} updated successfully", id);
        return ResponseFactory.ok(response);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {
        log.info("API CALL: DELETE /api/ingredients/{} — Deleting ingredient", id);
        ingredientService.delete(id);
        log.info("API RESPONSE: Ingredient {} deleted successfully", id);
        return ResponseFactory.noContent();
    }
}
