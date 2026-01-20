package com.myrecipewhisper.backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role = "USER";

    @Column(name = "provider", nullable = false)
    private String provider = "LOCAL";

    @Column(name = "enabled_account", nullable = false)
    private Boolean enabledAccount = false;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

}
