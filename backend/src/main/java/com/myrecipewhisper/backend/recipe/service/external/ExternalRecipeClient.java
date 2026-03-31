package com.myrecipewhisper.backend.recipe.service.external;

import java.util.List;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.myrecipewhisper.backend.common.configs.SpoonacularProperties;
import com.myrecipewhisper.backend.recipe.dto.ExternalRecipeResponseDTO;
import com.myrecipewhisper.backend.recipe.dto.external.ExternalRecipeItemDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalRecipeClient {

    private final RestTemplate restTemplate;
    private final SpoonacularProperties properties;

    public ExternalRecipeResponseDTO searchRecipes(List<String> ingredients, String cuisine, String tags) {
        log.info("RAPIDAPI HOST LOADED = {}", properties.api().host());

        String url = UriComponentsBuilder
                .fromHttpUrl(properties.endpoints().search())
                .queryParam("includeIngredients", String.join(",", ingredients))
                .queryParam("number", 20)
                .queryParam("cuisine", cuisine)
                .queryParam("tags", tags)
                .queryParam("addRecipeInformation", true)
                .toUriString();

        log.info("Calling RapidAPI: {}", url);

        HttpHeaders headers = getHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            return getResponse(url, entity, ExternalRecipeResponseDTO.class);
        } catch (Exception e) {
            log.error("Error calling RapidAPI: {}", e.getMessage());
            return null;
        }
    }

    public ExternalRecipeItemDTO getRecipeDetails(Integer recipeId) {
        log.info("Fetching details for recipe ID: {}", recipeId);

        String url = UriComponentsBuilder
                .fromHttpUrl(properties.endpoints().details())
                .build(recipeId)
                .toString();

        log.info("Calling RapidAPI details: {}", url);

        HttpHeaders headers = getHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            return getResponse(url, entity, ExternalRecipeItemDTO.class);
        } catch (Exception e) {
            log.error("Error calling RapidAPI for recipe details: {}", e.getMessage());
            return null;
        }
    }

    private <T> T getResponse(String url, HttpEntity<Void> entity, Class<T> responseType) {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", properties.api().key());
        headers.set("X-RapidAPI-Host", properties.api().host());
        return headers;
    }
}
