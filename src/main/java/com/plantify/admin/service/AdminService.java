package com.plantify.admin.service;

import com.plantify.admin.domain.dto.response.UserResponse;
import com.plantify.admin.domain.entity.Role;

public interface AdminService {
    UserResponse updateUserRole(String authorizationHeader, Role newRole);
}
