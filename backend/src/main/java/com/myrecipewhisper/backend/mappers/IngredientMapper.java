package com.myrecipewhisper.backend.mappers;

import org.springframework.stereotype.Component;

import com.myrecipewhisper.backend.dtos.ingredient.CreateIngredientDTO;
import com.myrecipewhisper.backend.dtos.ingredient.IngredientResponseDTO;
import com.myrecipewhisper.backend.entities.Ingredient;

@Component
public class IngredientMapper {

    public Ingredient toEntity(CreateIngredientDTO dto) {
        if (dto == null)
            return null;
        var ingredient = new Ingredient();
        ingredient.setName(dto.name());
        return ingredient;
    }

    public IngredientResponseDTO toDTO(Ingredient entity) {
        if (entity == null)
            return null;
        var dto = new IngredientResponseDTO(
                entity.getId(),
                entity.getName());
        return dto;
    }

}
