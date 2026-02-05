package com.myrecipewhisper.backend.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spoonacular")
public record SpoonacularProperties(

        Api api,
        Endpoints endpoints) {

    public record Api(
            String key,
            String host) {

    }

    public record Endpoints(
            String search) {
    }
}
