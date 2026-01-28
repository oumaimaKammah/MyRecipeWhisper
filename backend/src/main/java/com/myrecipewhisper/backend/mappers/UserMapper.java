package com.myrecipewhisper.backend.mappers;

import com.myrecipewhisper.backend.dtos.user.CreateUserDTO;
import com.myrecipewhisper.backend.dtos.user.UpdateUserDTO;
import com.myrecipewhisper.backend.dtos.user.UserDTO;
import com.myrecipewhisper.backend.entities.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBirthday(),
                user.getRegisteredAt());
    }

    public static User toUserEntity(CreateUserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        var user = new User();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setBirthday(userDTO.birthday());
        return user;
    }

    public static void updateUserFromDTO(User user, UpdateUserDTO userDTO) {
        if (userDTO == null || user == null) {
            return;
        }
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setBirthday(userDTO.birthday());
    }

}
