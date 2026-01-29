package com.myrecipewhisper.backend.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myrecipewhisper.backend.dtos.cuisine.CreateCuisineDTO;
import com.myrecipewhisper.backend.dtos.cuisine.CuisineDTO;
import com.myrecipewhisper.backend.entities.Cuisine;
import com.myrecipewhisper.backend.mappers.CuisineMapper;
import com.myrecipewhisper.backend.repositories.CuisineRepository;
import com.myrecipewhisper.backend.validators.CuisineValidator;

@Service
@RequiredArgsConstructor
@Slf4j
public class CuisineService {

    private final CuisineRepository cuisineRepository;
    private final CuisineValidator cuisineValidator;

    /**
     * Retrieve all cuisines.
     */
    @Transactional(readOnly = true)
    public List<CuisineDTO> getAllCuisines() {
        log.info("Fetching all cuisines");
        return cuisineRepository.findAll()
                .stream()
                .map(CuisineMapper::toDTO)
                .toList();
    }

    /**
     * Retrieve a cuisine by its ID.
     */
    @Transactional(readOnly = true)
    public CuisineDTO getCuisineById(Integer id) {
        log.info("Fetching cuisine with id={}", id);
        var cuisine = cuisineValidator.validateCuisineExists(id);
        return CuisineMapper.toDTO(cuisine);
    }

    /**
     * Create a new cuisine (ADMIN ONLY).
     */
    @Transactional
    public CuisineDTO createCuisine(CreateCuisineDTO dto) {
        log.info("Creating new cuisine with name='{}'", dto.name());

        cuisineValidator.validateNameUnique(dto.name());
        log.debug("Cuisine name '{}' is unique", dto.name());

        var cuisine = new Cuisine();
        cuisine.setCuisineName(dto.name());

        var saved = cuisineRepository.save(cuisine);
        log.info("Cuisine created successfully with id={}", saved.getId());

        return CuisineMapper.toDTO(saved);
    }

    /**
     * Update an existing cuisine (ADMIN ONLY).
     */
    @Transactional
    public CuisineDTO updateCuisine(Integer id, CreateCuisineDTO dto) {
        log.info("Updating cuisine id={} with new name='{}'", id, dto.name());

        var cuisine = cuisineValidator.validateCuisineExists(id);

        cuisineValidator.validateNameUnique(dto.name());
        log.debug("New cuisine name '{}' is unique", dto.name());

        cuisine.setCuisineName(dto.name());

        log.info("Cuisine id={} updated successfully", id);
        return CuisineMapper.toDTO(cuisine);
    }

    /**
     * Delete a cuisine (ADMIN ONLY).
     */
    @Transactional
    public void deleteCuisine(Integer id) {
        log.info("Deleting cuisine with id={}", id);

        Cuisine cuisine = cuisineValidator.validateCuisineExists(id);
        cuisineRepository.delete(cuisine);

        log.info("Cuisine id={} deleted successfully", id);
    }
}
