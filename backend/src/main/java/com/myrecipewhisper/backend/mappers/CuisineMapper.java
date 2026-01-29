package com.myrecipewhisper.backend.mappers;

import com.myrecipewhisper.backend.dtos.cuisine.CreateCuisineDTO;
import com.myrecipewhisper.backend.dtos.cuisine.CuisineDTO;
import com.myrecipewhisper.backend.dtos.cuisine.UpdateCuisineDTO;
import com.myrecipewhisper.backend.entities.Cuisine;

public class CuisineMapper {

    /** * Convert Cuisine entity to CuisineDTO. */
    public static CuisineDTO toDTO(Cuisine cuisine) {
        if (cuisine == null)
            return null;
        return new CuisineDTO(cuisine.getId(), cuisine.getCuisineName());
    }

    /**
     * * Convert CreateCuisineDTO to a new Cuisine entity. * Used for creation
     * (ADMIN).
     */
    public static Cuisine toEntity(CreateCuisineDTO dto) {
        if (dto == null)
            return null;
        Cuisine cuisine = new Cuisine();
        cuisine.setCuisineName(dto.name());
        return cuisine;
    }

    /**
     * * Update an existing Cuisine entity using UpdateCuisineDTO. * Used for update
     * (ADMIN).
     */
    public static void updateEntity(Cuisine cuisine, UpdateCuisineDTO dto) {
        if (cuisine == null || dto == null)
            return;
        cuisine.setCuisineName(dto.name());
    }

}
