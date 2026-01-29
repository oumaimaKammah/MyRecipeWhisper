package com.myrecipewhisper.backend.services.impl;

import com.myrecipewhisper.backend.dtos.ingredient.CreateIngredientDTO;
import com.myrecipewhisper.backend.dtos.ingredient.IngredientResponseDTO;
import com.myrecipewhisper.backend.dtos.ingredient.UpdateIngredientDTO;
import com.myrecipewhisper.backend.entities.Ingredient;
import com.myrecipewhisper.backend.exceptions.ingredient.IngredientNotFoundException;
import com.myrecipewhisper.backend.mappers.IngredientMapper;
import com.myrecipewhisper.backend.repositories.IngredientRepository;
import com.myrecipewhisper.backend.validators.IngredientValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;
    private final IngredientValidator ingredientValidator;

    @Transactional
    public IngredientResponseDTO create(CreateIngredientDTO dto) {
        log.info("Creating new ingredient with name: {}", dto.name());

        ingredientValidator.validateCreate(dto);

        var ingredient = ingredientMapper.toEntity(dto);
        ingredientRepository.save(ingredient);

        log.info("Ingredient created successfully with ID: {}", ingredient.getId());
        return ingredientMapper.toDTO(ingredient);
    }

    @Transactional(readOnly = true)
    public List<IngredientResponseDTO> getAll() {
        log.debug("Fetching all ingredients");

        var ingredients = ingredientRepository.findAll()
                .stream()
                .map(ingredientMapper::toDTO)
                .toList();

        log.info("Fetched {} ingredients", ingredients.size());
        return ingredients;
    }

    @Transactional(readOnly = true)
    public IngredientResponseDTO gIngredientResponseDTOetById(Integer id) {
        log.info("Fetching ingredient with id {}", id);
        var ingredient = ingredientRepository.getIngredientById(id)
                .orElseThrow(() -> {
                    log.error("Ingredient with id {} not found", id);
                    return new IngredientNotFoundException(id);

                });
        log.info("Ingredient with id {} fetched successfully", id);
        var dto = ingredientMapper.toDTO(ingredient);
        return dto;
    }

    @Transactional
    public IngredientResponseDTO update(Integer id, UpdateIngredientDTO dto) {
        log.info("Updating ingredient with ID: {}", id);

        ingredientValidator.validateUpdate(id, dto);

        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow();
        ingredient.setName(dto.name());
        log.info("Ingredient with ID {} updated successfully", id);
        return ingredientMapper.toDTO(ingredient);
    }

    @Transactional
    public void delete(Integer id) {
        log.info("Deleting ingredient with ID: {}", id);

        if (!ingredientRepository.existsById(id)) {
            log.error("Ingredient deletion failed: ID {} not found", id);
            throw new IngredientNotFoundException(id);
        }

        ingredientRepository.deleteById(id);

        log.info("Ingredient with ID {} deleted successfully", id);
    }
}
