package com.myrecipewhisper.backend.validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.myrecipewhisper.backend.entities.Cuisine;
import com.myrecipewhisper.backend.exceptions.cuisine.CuisineAlreadyExistsException;
import com.myrecipewhisper.backend.exceptions.cuisine.CuisineNotFoundException;
import com.myrecipewhisper.backend.repositories.CuisineRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class CuisineValidator {
    public final CuisineRepository cuisineRepository;

    public void validateNameUnique(String cuisineName) {
        log.debug("Validating cuisine name uniqueness: {}", cuisineName);

        cuisineRepository.findByCuisineNameIgnoreCase(cuisineName)
                .ifPresent(cuisine -> {
                    log.warn("Validation failed: cuisine '{}' already exists", cuisineName);
                    throw new CuisineAlreadyExistsException(cuisineName);
                });
    }

    public Cuisine validateCuisineExists(Integer id) {
        log.debug("Validating existence of cuisine with ID: {}", id);
        return cuisineRepository.findById(id).orElseThrow(() -> {
            log.error("Cuisine with ID {} not found", id);
            return new CuisineNotFoundException("Cuisine with ID " + id + " does not exist");
        });
    }

}
