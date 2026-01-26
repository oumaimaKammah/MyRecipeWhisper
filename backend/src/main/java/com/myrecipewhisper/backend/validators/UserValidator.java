package com.myrecipewhisper.backend.validators;

import org.springframework.stereotype.Component;
import com.myrecipewhisper.backend.exceptions.EmailAlreadyUsedException;
import com.myrecipewhisper.backend.exceptions.RessourceNotFoundException;
import com.myrecipewhisper.backend.exceptions.UsernameAlreadyUsedException;
import com.myrecipewhisper.backend.repositories.UserRepository;

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
            throw new EmailAlreadyUsedException("Email already in use: " + email);
        }
        if (!isUsernameUnique(username)) {
            throw new UsernameAlreadyUsedException("Username already in use: " + username);
        }

    }

    public void validateUpdate(Integer userId, String newEmail, String newUsername) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RessourceNotFoundException("User not found with id: " + userId));

        if (!user.getEmail().equals(newEmail) && !isEmailUnique(newEmail)) {
            throw new EmailAlreadyUsedException("Email already in use: " + newEmail);
        }

        if (!user.getUsername().equals(newUsername) && !isUsernameUnique(newUsername)) {
            throw new UsernameAlreadyUsedException("Username already in use: " + newUsername);
        }
    }

}
