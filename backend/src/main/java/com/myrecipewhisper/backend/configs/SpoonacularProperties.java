package com.myrecipewhisper.backend.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spoonacular")
public class SpoonacularProperties {

    private Api api = new Api();
    private Endpoints endpoints = new Endpoints();
    private Defaults defaults = new Defaults();

    @Getter
    @Setter
    public static class Api {
        private String key;
    }

    @Getter
    @Setter
    public static class Endpoints {
        private String search;
    }

    @Getter
    @Setter
    public static class Defaults {
        private int number;
        private boolean addRecipeInformation;
    }
}
