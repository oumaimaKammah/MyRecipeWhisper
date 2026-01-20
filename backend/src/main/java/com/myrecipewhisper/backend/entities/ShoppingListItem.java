package com.myrecipewhisper.backend.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopping_list_items")
public class ShoppingListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id", nullable = false)
    private ShoppingList shoppingList;

    @Column(name = "ingredient", nullable = false)
    private String ingredient;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "checked", nullable = false)
    private Boolean checked;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
