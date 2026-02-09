package com.myrecipewhisper.backend.user.service.impl;

import com.myrecipewhisper.backend.user.dto.CreateUserDTO;
import com.myrecipewhisper.backend.user.dto.UpdateUserDTO;
import com.myrecipewhisper.backend.user.entity.User;
import com.myrecipewhisper.backend.user.mapper.UserMapper;
import com.myrecipewhisper.backend.user.repository.UserRepository;
import com.myrecipewhisper.backend.user.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

import com.myrecipewhisper.backend.exceptions.user.UserNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.info("Fetching all users from the database");
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Integer userId) {
        log.info("Fetching user with id: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Transactional
    public User createUser(CreateUserDTO dto) {
        if (dto == null) {
            log.error("CreateUserDTO is null");
            throw new IllegalArgumentException("CreateUserDTO cannot be null");
        }
        userValidator.validateCreate(dto.email(), dto.username());

        var user = UserMapper.toUserEntity(dto);
        var saved = userRepository.save(user);
        log.info("User created successfully with id {}", saved.getId());

        return saved;
    }

    @Transactional
    public User updateUser(Integer userId, UpdateUserDTO dto) {
        if (dto == null) {
            log.error("UpdateUserDTO is null");
            throw new IllegalArgumentException("UpdateUserDTO cannot be null");
        }
        userValidator.validateUpdate(userId, dto.email(), dto.username());

        User user = getUserById(userId);
        UserMapper.updateUserFromDTO(user, dto);
        log.info("User with id {} updated successfully", user.getId());

        return user;
    }

    @Transactional
    public void deleteUserById(Integer userId) {
        var user = getUserById(userId);
        userRepository.delete(user);
        log.info("User with id {} deleted successfully", userId);
    }

}