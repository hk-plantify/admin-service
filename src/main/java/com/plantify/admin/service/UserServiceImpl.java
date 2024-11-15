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
    public List<UserResponse> getAllUsers() {
        authenticationService.validateAdminRole();
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(Long userId) {
        authenticationService.validateAdminRole();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        return UserResponse.from(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest request) {
        authenticationService.validateAdminRole();
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
    public void deleteUser(Long userId) {
        authenticationService.validateAdminRole();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        userRepository.delete(user);
    }
}
