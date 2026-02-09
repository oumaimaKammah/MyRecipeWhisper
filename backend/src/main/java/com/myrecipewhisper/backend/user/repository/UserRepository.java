package com.myrecipewhisper.backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myrecipewhisper.backend.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
