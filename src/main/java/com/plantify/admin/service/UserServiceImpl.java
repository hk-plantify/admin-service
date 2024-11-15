package com.plantify.admin.service;

import com.plantify.admin.domain.dto.request.UserRequest;
import com.plantify.admin.domain.dto.response.UserResponse;
import com.plantify.admin.domain.entity.User;
import com.plantify.admin.global.exception.ApplicationException;
import com.plantify.admin.global.exception.errorcode.UserErrorCode;
import com.plantify.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    @Override
    public List<UserResponse> getAllUsers(String authorizationHeader) {
        authenticationService.validateAdminRole(authorizationHeader);
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(String authorizationHeader, Long userId) {
        authenticationService.validateAdminRole(authorizationHeader);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        return UserResponse.from(user);
    }

    @Override
    public UserResponse updateUser(String authorizationHeader, Long userId, UserRequest request) {
        authenticationService.validateAdminRole(authorizationHeader);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        User updatedUser = user.toBuilder()
                .username(request.username())
                .role(request.role())
                .build();

        User savedUser = userRepository.save(updatedUser);
        return UserResponse.from(savedUser);
    }

    @Override
    public void deleteUser(String authorizationHeader, Long userId) {
        authenticationService.validateAdminRole(authorizationHeader);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        userRepository.delete(user);
    }
}
