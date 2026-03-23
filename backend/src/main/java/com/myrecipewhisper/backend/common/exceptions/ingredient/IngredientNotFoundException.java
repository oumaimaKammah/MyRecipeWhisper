package com.myrecipewhisper.backend.common.exceptions.ingredient;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Integer id) {
        super("Ingredient with ID " + id + " not found");
    }
}
