package com.myrecipewhisper.backend.validators;

import org.springframework.stereotype.Component;

import com.myrecipewhisper.backend.entities.User;
import com.myrecipewhisper.backend.exceptions.user.EmailAlreadyUsedException;
import com.myrecipewhisper.backend.exceptions.user.UserNotFoundException;
import com.myrecipewhisper.backend.exceptions.user.UsernameAlreadyUsedException;
import com.myrecipewhisper.backend.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserValidator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    private boolean isUsernameUnique(String username) {
        return !userRepository.existsByUsername(username);
    }

    public void validateCreate(String email, String username) {

        if (!isEmailUnique(email)) {
            log.warn("Email already in use: {}", email);
            throw new EmailAlreadyUsedException("Email already in use: " + email);
        }
        if (!isUsernameUnique(username)) {
            log.warn("Username already in use: {}", username);
            throw new UsernameAlreadyUsedException("Username already in use: " + username);
        }

    }

    public void validateUpdate(Integer userId, String newEmail, String newUsername) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            log.error("User not found with id {}", userId);
            return new UserNotFoundException("User not found with id: " + userId);
        });
        if (!user.getEmail().equals(newEmail) && !isEmailUnique(newEmail)) {
            throw new EmailAlreadyUsedException("Email already in use: " + newEmail);
        }

        if (!user.getUsername().equals(newUsername) && !isUsernameUnique(newUsername)) {
            throw new UsernameAlreadyUsedException("Username already in use: " + newUsername);
        }
    }

}
