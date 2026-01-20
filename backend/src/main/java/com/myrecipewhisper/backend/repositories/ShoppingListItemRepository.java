package com.myrecipewhisper.backend.repositories;

import com.myrecipewhisper.backend.entities.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Integer> {

}
