package com.plantify.admin.controller;

import com.plantify.admin.domain.dto.response.UserResponse;
import com.plantify.admin.domain.entity.Role;
import com.plantify.admin.global.response.ApiResponse;
import com.plantify.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/users")
    public ResponseEntity<ApiResponse<UserResponse>> updateUserRole(
            @RequestHeader("Authorization") String authorizationHeader, @RequestParam Role role) {
        UserResponse updatedUserResponse = adminService.updateUserRole(authorizationHeader, role);
        return ResponseEntity.ok(ApiResponse.ok(updatedUserResponse));
    }

}
