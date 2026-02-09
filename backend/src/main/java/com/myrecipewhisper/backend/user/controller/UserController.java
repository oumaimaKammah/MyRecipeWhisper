package com.myrecipewhisper.backend.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.myrecipewhisper.backend.user.dto.CreateUserDTO;
import com.myrecipewhisper.backend.user.dto.UpdateUserDTO;
import com.myrecipewhisper.backend.user.mapper.UserMapper;
import com.myrecipewhisper.backend.user.service.impl.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.myrecipewhisper.backend.api.ResponseFactory;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Object getAllUsers() {
        log.info("API CALL: GET /api/users --Fetching all users");
        var response = userService.getAllUsers()
                .stream()
                .map(UserMapper::toUserDTO)
                .toList();
        log.info("API RESPONSE: {} users fetched successfully", response.size());
        return ResponseFactory.ok(response);
    }

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Integer id) {
        log.info("API CALL: GET /api/users/{} --Fetching user", id);
        var user = userService.getUserById(id);
        log.info("API RESPONSE: User {} fetched successfully", id);
        var response = UserMapper.toUserDTO(user);
        return ResponseFactory.ok(response);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@Valid @RequestBody CreateUserDTO dto) {
        log.info("API CALL: POST /api/users --Creating user");
        var user = userService.createUser(dto);
        log.info("API RESPONSE: User created with ID {}", user.getId());
        var response = UserMapper.toUserDTO(user);
        return ResponseFactory.created(response);
    }

    @PutMapping("/{id}")
    public Object updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserDTO dto) {
        log.info("API CALL: PUT /api/users/{} --Updating user", id);
        var user = userService.updateUser(id, dto);
        log.info("API RESPONSE: User {} updated successfully", id);
        var response = UserMapper.toUserDTO(user);
        return ResponseFactory.ok(response);
    }

    @DeleteMapping("/{id}")
    public Object deleteUser(@PathVariable Integer id) {
        log.info("API CALL: DELETE /api/users/{} --Deleting user", id);
        userService.deleteUserById(id);
        log.info("API RESPONSE: User {} deleted successfully", id);
        return ResponseFactory.noContent();
    }

}
