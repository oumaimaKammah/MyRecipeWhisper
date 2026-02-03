package com.myrecipewhisper.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MyRecipeWhisperBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRecipeWhisperBackendApplication.class, args);
	}

}
