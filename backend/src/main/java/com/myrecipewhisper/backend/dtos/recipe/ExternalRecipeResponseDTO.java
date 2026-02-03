package com.myrecipewhisper.backend.dtos.recipe;

import java.util.List;

public record ExternalRecipeResponseDTO(
        List<ExternalRecipeItemDTO> results) {

}
