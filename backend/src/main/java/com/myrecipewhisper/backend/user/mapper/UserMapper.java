package com.myrecipewhisper.backend.user.mapper;

import com.myrecipewhisper.backend.user.dto.CreateUserDTO;
import com.myrecipewhisper.backend.user.dto.UpdateUserDTO;
import com.myrecipewhisper.backend.user.dto.UserDTO;
import com.myrecipewhisper.backend.user.entity.User;

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
                user.getRegisteredAt(),
                user.getRole().name());
    }

    public static User toUserEntity(CreateUserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        var user = new User();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
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
