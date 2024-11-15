package com.plantify.admin.service;

import com.plantify.admin.domain.dto.request.UserRequest;
import com.plantify.admin.domain.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers(String authorizationHeader);
    UserResponse getUser(String authorizationHeader, Long userId);
    UserResponse updateUser(String authorizationHeader, Long userId, UserRequest request);
    void deleteUser(String authorizationHeader, Long userId);
}
