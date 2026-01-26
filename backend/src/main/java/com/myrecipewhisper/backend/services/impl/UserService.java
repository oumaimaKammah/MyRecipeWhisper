package com.myrecipewhisper.backend.services.impl;

import com.myrecipewhisper.backend.repositories.UserRepository;
import com.myrecipewhisper.backend.dtos.CreateUserDTO;
import com.myrecipewhisper.backend.dtos.UpdateUserDTO;
import com.myrecipewhisper.backend.entities.User;
import com.myrecipewhisper.backend.exceptions.RessourceNotFoundException;
import com.myrecipewhisper.backend.mappers.UserMapper;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RessourceNotFoundException("User not found with id: " + userId));
    }

    @Transactional
    public User createUser(CreateUserDTO dto) {
        if(dto == null) {
            throw new IllegalArgumentException("CreateUserDTO cannot be null");
        }
        if(dto.username() == null || dto.email() == null || dto.password() == null) {
            throw new IllegalArgumentException("Username, email, and password are required fields");
        }
        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email already in use: " + dto.email());
            
        }
        if (userRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("Username already in use: " + dto.username());
        }
        var user = UserMapper.toUserEntity(dto);
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Integer userId, UpdateUserDTO dto) {
        var user = getUserById(userId);

        UserMapper.updateUserFromDTO(user,dto); 

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Integer userId) {
        var user = getUserById(userId);
        userRepository.delete(user);
    }

}