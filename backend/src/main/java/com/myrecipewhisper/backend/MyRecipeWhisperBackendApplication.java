package com.myrecipewhisper.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.myrecipewhisper.backend.common.configs.SpoonacularProperties;

@SpringBootApplication
@EnableConfigurationProperties(SpoonacularProperties.class)
public class MyRecipeWhisperBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRecipeWhisperBackendApplication.class, args);
	}

}
