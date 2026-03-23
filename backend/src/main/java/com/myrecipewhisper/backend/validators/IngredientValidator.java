package com.myrecipewhisper.backend.validators;

import com.myrecipewhisper.backend.common.exceptions.ingredient.IngredientAlreadyExistsException;
import com.myrecipewhisper.backend.common.exceptions.ingredient.IngredientNotFoundException;
import com.myrecipewhisper.backend.dtos.ingredient.CreateIngredientDTO;
import com.myrecipewhisper.backend.dtos.ingredient.UpdateIngredientDTO;
import com.myrecipewhisper.backend.repositories.IngredientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IngredientValidator {

    private final IngredientRepository ingredientRepository;

    public void validateCreate(CreateIngredientDTO dto) {
        log.debug("Validating ingredient creation: {}", dto.name());

        ingredientRepository.findByNameIgnoreCase(dto.name())
                .ifPresent(i -> {
                    log.warn("Validation failed: ingredient '{}' already exists", dto.name());
                    throw new IngredientAlreadyExistsException(dto.name());
                });
    }

    public void validateUpdate(Integer id, UpdateIngredientDTO dto) {
        log.debug("Validating ingredient update for ID {}: {}", id, dto.name());

        if (!ingredientRepository.existsById(id)) {
            log.error("Validation failed: ingredient ID {} not found", id);
            throw new IngredientNotFoundException(id);
        }

        ingredientRepository.findByNameIgnoreCase(dto.name())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        log.warn("Validation failed: ingredient '{}' already exists", dto.name());
                        throw new IngredientAlreadyExistsException(dto.name());
                    }
                });
    }
}
