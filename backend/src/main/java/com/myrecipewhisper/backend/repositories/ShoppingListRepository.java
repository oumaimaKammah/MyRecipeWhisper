package com.myrecipewhisper.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myrecipewhisper.backend.entities.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

}
