package com.myrecipewhisper.backend.mappers;

import com.myrecipewhisper.backend.dtos.cuisine.CuisineDTO;
import com.myrecipewhisper.backend.entities.Cuisine;

public class CuisineMapper {

    public static CuisineDTO toDTO(Cuisine cuisine) {
        return new CuisineDTO(
                cuisine.getId(),
                cuisine.getCuisineName());
    }

}
