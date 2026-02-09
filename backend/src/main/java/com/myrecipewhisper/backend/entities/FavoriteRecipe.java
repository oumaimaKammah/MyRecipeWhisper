package com.myrecipewhisper.backend.entities;

import java.time.LocalDateTime;

import com.myrecipewhisper.backend.user.entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorite_recipes")
public class FavoriteRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "recipe_id", nullable = false)
    private Integer recipeId;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;
}
