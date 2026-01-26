package com.myrecipewhisper.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myrecipewhisper.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
