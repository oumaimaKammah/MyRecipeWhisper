package com.myrecipewhisper.backend.services.external;

import com.myrecipewhisper.backend.configs.SpoonacularProperties;
import com.myrecipewhisper.backend.dtos.recipe.ExternalRecipeResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalRecipeClient {

    private final RestTemplate restTemplate;
    private final SpoonacularProperties properties;

    public ExternalRecipeResponseDTO searchRecipes(String ingredients, String cuisineName) {

        String uri = UriComponentsBuilder
                .fromHttpUrl(properties.getEndpoints().getSearch())
                .queryParam("apiKey", properties.getApi().getKey())
                .queryParam("includeIngredients", ingredients)
                .queryParam("cuisine", cuisineName)
                .queryParam("number", properties.getDefaults().getNumber())
                .queryParam("addRecipeInformation", properties.getDefaults().isAddRecipeInformation())
                .toUriString();

        log.info("Calling Spoonacular API: {}", uri);

        try {
            return restTemplate.getForObject(uri, ExternalRecipeResponseDTO.class);
        } catch (Exception ex) {
            log.error("Error calling Spoonacular API: {}", ex.getMessage());
            return null;
        }
    }
}
