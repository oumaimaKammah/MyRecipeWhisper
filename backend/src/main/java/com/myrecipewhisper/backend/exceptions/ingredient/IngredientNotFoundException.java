package com.myrecipewhisper.backend.exceptions.ingredient;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Integer id) {
        super("Ingredient with ID " + id + " not found");
    }
}
