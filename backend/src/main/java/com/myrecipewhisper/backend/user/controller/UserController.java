package com.myrecipewhisper.backend.user.controller;

import com.myrecipewhisper.backend.api.ResponseFactory;
import com.myrecipewhisper.backend.user.dto.CreateUserDTO;
import com.myrecipewhisper.backend.user.dto.UpdateUserDTO;
import com.myrecipewhisper.backend.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Object getAllUsers() {
        log.info("[USER] GET /api/users - Fetching all users");
        var users = userService.getAllUsers();
        log.info("[USER] {} users fetched successfully", users.size());
        return ResponseFactory.ok(users);
    }

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable Integer id) {
        log.info("[USER] GET /api/users/{} - Fetching user", id);
        var user = userService.getUserById(id);
        log.info("[USER] User {} fetched successfully", id);
        return ResponseFactory.ok(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object createUser(@Valid @RequestBody CreateUserDTO dto) {
        log.info("[USER] POST /api/users - Creating new user");
        var createdUser = userService.createUser(dto);
        log.info("[USER] User created successfully with ID {}", createdUser.userId());
        return ResponseFactory.created(createdUser);
    }

    @PutMapping("/{id}")
    public Object updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUserDTO dto) {
        log.info("[USER] PUT /api/users/{} - Updating user", id);
        var updatedUser = userService.updateUser(id, dto);
        log.info("[USER] User {} updated successfully", id);
        return ResponseFactory.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public Object deleteUser(@PathVariable Integer id) {
        log.info("[USER] DELETE /api/users/{} - Deleting user", id);
        userService.deleteUserById(id);
        log.info("[USER] User {} deleted successfully", id);
        return ResponseFactory.noContent();
    }
}
