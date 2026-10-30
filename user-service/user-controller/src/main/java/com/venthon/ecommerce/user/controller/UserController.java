package com.venthon.ecommerce.user.controller;

import com.venthon.ecommerce.user.dto.UserCreateRequest;
import com.venthon.ecommerce.user.dto.UserUpdateRequest;
import com.venthon.ecommerce.user.dto.UserResponse;
import com.venthon.ecommerce.user.service.UserService;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse create(
            @Valid @RequestBody UserCreateRequest request
    ) {
        return userService.create(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable String id,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        return userService.update(id, request);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(
            @PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserResponse getUserByUsername(
            @PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/role/{roleName}")
    public List<UserResponse> getUsersByRoleName(
            @PathVariable String roleName) {
        return userService.getUsersByRoleName(roleName);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}