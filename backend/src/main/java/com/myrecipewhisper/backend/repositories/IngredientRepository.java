package com.myrecipewhisper.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myrecipewhisper.backend.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Optional<Ingredient> findByNameIgnoreCase(String name);

    Optional<Ingredient> getIngredientById(Integer id);
}
