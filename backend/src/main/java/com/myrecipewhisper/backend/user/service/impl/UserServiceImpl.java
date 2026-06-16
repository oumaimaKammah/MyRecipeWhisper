package com.myrecipewhisper.backend.user.service.impl;

import com.myrecipewhisper.backend.common.exceptions.user.UserNotFoundException;
import com.myrecipewhisper.backend.user.dto.CreateUserDTO;
import com.myrecipewhisper.backend.user.dto.UpdateUserDTO;
import com.myrecipewhisper.backend.user.dto.UserDTO;
import com.myrecipewhisper.backend.user.entity.User;
import com.myrecipewhisper.backend.user.mapper.UserMapper;
import com.myrecipewhisper.backend.user.repository.UserRepository;
import com.myrecipewhisper.backend.user.service.UserService;
import com.myrecipewhisper.backend.user.validator.UserValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Integer userId) {
        log.info("Fetching user with id {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        return UserMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public UserDTO createUser(CreateUserDTO dto) {
        log.info("Creating new user");

        userValidator.validateCreate(dto.email(), dto.username());

        User user = UserMapper.toUserEntity(dto);

        user.setPassword(passwordEncoder.encode(dto.password()));

        User saved = userRepository.save(user);

        log.info("User created successfully with id {}", saved.getId());
        return UserMapper.toUserDTO(saved);
    }

    @Override
    @Transactional
    public UserDTO updateUser(Integer userId, UpdateUserDTO dto) {
        log.info("Updating user with id {}", userId);

        userValidator.validateUpdate(userId, dto.email(), dto.username());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        UserMapper.updateUserFromDTO(user, dto);

        log.info("User {} updated successfully", userId);
        return UserMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Integer userId) {
        log.info("Deleting user with id {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        userRepository.delete(user);

        log.info("User {} deleted successfully", userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        log.info("Finding user by email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }
}
