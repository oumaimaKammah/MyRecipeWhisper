package com.myrecipewhisper.backend.user.service;

import com.myrecipewhisper.backend.user.dto.CreateUserDTO;
import com.myrecipewhisper.backend.user.dto.UpdateUserDTO;
import com.myrecipewhisper.backend.user.dto.UserDTO;
import com.myrecipewhisper.backend.user.entity.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Integer userId);

    UserDTO createUser(CreateUserDTO dto);

    UserDTO updateUser(Integer userId, UpdateUserDTO dto);

    void deleteUserById(Integer userId);
}
