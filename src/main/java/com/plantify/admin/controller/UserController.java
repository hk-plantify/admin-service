package com.plantify.admin.controller;

import com.plantify.admin.domain.dto.request.UserRequest;
import com.plantify.admin.domain.dto.response.UserResponse;
import com.plantify.admin.domain.entity.Role;
import com.plantify.admin.global.response.ApiResponse;
import com.plantify.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ApiResponse.ok(users);
    }

    @GetMapping("/users/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long userId) {
        UserResponse response = userService.getUser(userId);
        return ApiResponse.ok(response);
    }

    @PutMapping("/users/{userId}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable Long userId, @RequestBody UserRequest request) {
        UserResponse response = userService.updateUser(userId, request);
        return ApiResponse.ok(response);
    }

    @DeleteMapping("/users/{userId}")
    public ApiResponse<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ApiResponse.ok();
    }
}
