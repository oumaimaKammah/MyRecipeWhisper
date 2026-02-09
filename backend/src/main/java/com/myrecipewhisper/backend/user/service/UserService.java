package com.myrecipewhisper.backend.user.service;

import com.myrecipewhisper.backend.user.dto.CreateUserDTO;
import com.myrecipewhisper.backend.user.dto.UpdateUserDTO;
import com.myrecipewhisper.backend.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Integer userId);

    UserDTO createUser(CreateUserDTO dto);

    UserDTO updateUser(Integer userId, UpdateUserDTO dto);

    void deleteUserById(Integer userId);
}
