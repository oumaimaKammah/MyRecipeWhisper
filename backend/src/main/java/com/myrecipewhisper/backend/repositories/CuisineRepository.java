package com.myrecipewhisper.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.myrecipewhisper.backend.entities.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {
    Optional<Cuisine> findByCuisineNameIgnoreCase(String cuisineName);

}
