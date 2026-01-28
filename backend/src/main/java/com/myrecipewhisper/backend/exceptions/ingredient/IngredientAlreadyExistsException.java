package com.myrecipewhisper.backend.exceptions.ingredient;

public class IngredientAlreadyExistsException extends RuntimeException {
    public IngredientAlreadyExistsException(String name) {
        super("Ingredient '" + name + "' already exists");
    }
}
