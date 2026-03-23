package com.myrecipewhisper.backend.recipe.dto;

import java.util.List;

public record ExternalRecipeResponseDTO(
                List<ExternalRecipeItemDTO> results) {

}
