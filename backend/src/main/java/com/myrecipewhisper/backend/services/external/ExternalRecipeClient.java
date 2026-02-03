package com.myrecipewhisper.backend.services.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.myrecipewhisper.backend.dtos.recipe.ExternalRecipeResponseDTO;

@Slf4j
@Service
public class ExternalRecipeClient {

    private RestTemplate restTemplate;
    private String apiKey;
    private String searchEndpoint;
    private int defaultNumber;
    private boolean addRecipeInformation;

    public ExternalRecipeClient(
            RestTemplate restTemplate,
            @Value("${spoonacular.api.key}") String apiKey,
            @Value("${spoonacular.endpoints.search}") String searchEndpoint,
            @Value("${spoonacular.defaults.number}") int defaultNumber,
            @Value("${spoonacular.defaults.addRecipeInformation}") boolean addRecipeInformation) {

        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.searchEndpoint = searchEndpoint;
        this.defaultNumber = defaultNumber;
        this.addRecipeInformation = addRecipeInformation;
    }

    public ExternalRecipeResponseDTO searchRecipes(String ingredients, String cuisineName) {

        String uri = UriComponentsBuilder.fromHttpUrl(searchEndpoint)
                .queryParam("apiKey", apiKey)
                .queryParam("includeIngredients", ingredients)
                .queryParam("cuisine", cuisineName)
                .queryParam("number", defaultNumber)
                .queryParam("addRecipeInformation", addRecipeInformation)
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
