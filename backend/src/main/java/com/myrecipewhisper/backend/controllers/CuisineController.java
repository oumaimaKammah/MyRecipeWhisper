package com.myrecipewhisper.backend.controllers;

import com.myrecipewhisper.backend.api.ResponseFactory;
import com.myrecipewhisper.backend.dtos.cuisine.CreateCuisineDTO;
import com.myrecipewhisper.backend.dtos.cuisine.CuisineDTO;
import com.myrecipewhisper.backend.services.impl.CuisineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuisines")
@RequiredArgsConstructor
@Slf4j
public class CuisineController {

    private final CuisineService cuisineService;

    /**
     * Retrieve all cuisines (PUBLIC).
     */
    @GetMapping
    public ResponseEntity<?> getAllCuisines() {
        log.info("GET /api/cuisines - Fetching all cuisines");
        List<CuisineDTO> cuisines = cuisineService.getAllCuisines();
        return ResponseFactory.ok(cuisines);
    }

    /**
     * Retrieve a cuisine by ID (PUBLIC).
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCuisineById(@PathVariable Integer id) {
        log.info("GET /api/cuisines/{} - Fetching cuisine by ID", id);
        CuisineDTO cuisine = cuisineService.getCuisineById(id);
        return ResponseFactory.ok(cuisine);
    }

    /**
     * Create a new cuisine (ADMIN ONLY).
     */
    @PostMapping
    public ResponseEntity<?> createCuisine(@Valid @RequestBody CreateCuisineDTO dto) {
        log.info("POST /api/cuisines - Creating new cuisine '{}'", dto.name());
        CuisineDTO created = cuisineService.createCuisine(dto);
        return ResponseFactory.created(created);
    }

    /**
     * Update an existing cuisine (ADMIN ONLY).
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuisine(
            @PathVariable Integer id,
            @Valid @RequestBody CreateCuisineDTO dto) {
        log.info("PUT /api/cuisines/{} - Updating cuisine with new name '{}'", id, dto.name());
        CuisineDTO updated = cuisineService.updateCuisine(id, dto);
        return ResponseFactory.ok(updated);
    }

    /**
     * Delete a cuisine (ADMIN ONLY).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuisine(@PathVariable Integer id) {
        log.info("DELETE /api/cuisines/{} - Deleting cuisine", id);
        cuisineService.deleteCuisine(id);
        return ResponseFactory.noContent();
    }

}
