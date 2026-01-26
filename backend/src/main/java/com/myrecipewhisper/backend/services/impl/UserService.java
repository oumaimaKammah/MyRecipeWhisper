package com.myrecipewhisper.backend.services.impl;

import com.myrecipewhisper.backend.repositories.UserRepository;
import com.myrecipewhisper.backend.validators.UserValidator;
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
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
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
        if (dto == null) {
            throw new IllegalArgumentException("CreateUserDTO cannot be null");
        }
        userValidator.validateCreate(dto.email(), dto.username());

        var user = UserMapper.toUserEntity(dto);

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Integer userId, UpdateUserDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UpdateUserDTO cannot be null");
        }
        userValidator.validateUpdate(userId, dto.email(), dto.username());

        User user = getUserById(userId);
        UserMapper.updateUserFromDTO(user, dto);

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Integer userId) {
        var user = getUserById(userId);
        userRepository.delete(user);
    }

}