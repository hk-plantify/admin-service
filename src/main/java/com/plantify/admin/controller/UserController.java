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
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers(
            @RequestHeader("Authorization") String authorizationHeader) {
        List<UserResponse> users = userService.getAllUsers(authorizationHeader);
        return ResponseEntity.ok(ApiResponse.ok(users));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable Long userId) {
        UserResponse response = userService.getUser(authorizationHeader, userId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable Long userId, @RequestParam UserRequest request) {
        UserResponse response = userService.updateUser(authorizationHeader, userId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable Long userId) {
        userService.deleteUser(authorizationHeader, userId);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
