package com.myrecipewhisper.backend.services.external;

import java.util.List;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.myrecipewhisper.backend.configs.SpoonacularProperties;
import com.myrecipewhisper.backend.dtos.recipe.ExternalRecipeResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalRecipeClient {

    private final RestTemplate restTemplate;
    private final SpoonacularProperties properties;

    public ExternalRecipeResponseDTO searchRecipes(List<String> ingredients, String cuisine) {
        log.info("RAPIDAPI HOST LOADED = {}", properties.api().host());

        String url = UriComponentsBuilder
                .fromHttpUrl(properties.endpoints().search())
                .queryParam("includeIngredients", String.join(",", ingredients))
                .queryParam("number", 20)
                .queryParam("addRecipeInformation", true)
                .queryParam("cuisine", cuisine)
                .toUriString();

        log.info("Calling RapidAPI: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", properties.api().key());
        headers.set("X-RapidAPI-Host", properties.api().host());

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<ExternalRecipeResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    ExternalRecipeResponseDTO.class);

            return response.getBody();

        } catch (Exception e) {
            log.error("Error calling RapidAPI: {}", e.getMessage());
            return null;
        }
    }
}
