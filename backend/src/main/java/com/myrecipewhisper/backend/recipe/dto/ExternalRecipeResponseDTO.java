package com.myrecipewhisper.backend.recipe.dto;

import java.util.List;

import com.myrecipewhisper.backend.recipe.dto.external.ExternalRecipeItemDTO;

public record ExternalRecipeResponseDTO(
        List<ExternalRecipeItemDTO> results) {

}
