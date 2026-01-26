package com.myrecipewhisper.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myrecipewhisper.backend.services.impl.UserService;
import com.myrecipewhisper.backend.dtos.UserDTO;
import com.myrecipewhisper.backend.mappers.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(UserMapper::toUserDTO)
                .toList();
    }
}
