package com.plantify.admin.service;

import com.plantify.admin.domain.dto.request.UserRequest;
import com.plantify.admin.domain.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    UserResponse getUser(Long userId);
    UserResponse updateUser(Long userId, UserRequest request);
    void deleteUser(Long userId);
}
