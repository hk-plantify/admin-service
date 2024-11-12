package com.plantify.admin.service;

import com.plantify.admin.controller.client.AuthServiceClient;
import com.plantify.admin.domain.dto.response.UserResponse;
import com.plantify.admin.domain.entity.Role;
import com.plantify.admin.domain.entity.User;
import com.plantify.admin.global.exception.ApplicationException;
import com.plantify.admin.global.exception.errorcode.AuthErrorCode;
import com.plantify.admin.global.exception.errorcode.UserErrorCode;
import com.plantify.admin.global.response.ApiResponse;
import com.plantify.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AuthServiceClient authServiceClient;
    private final UserRepository userRepository;

    public String refreshAccessToken(String authorizationHeader) {
        ApiResponse<String> response = authServiceClient.refreshAccessToken(authorizationHeader);
        if (response.getStatus() == HttpStatus.OK) {
            return response.getData();
        } else {
            throw createAuthException(response.getStatus());
        }
    }

    public UserResponse getUserInfo(String authorizationHeader) {
        ApiResponse<UserResponse> response = authServiceClient.getUserInfo(authorizationHeader);
        if (response.getStatus() == HttpStatus.OK) {
            return response.getData();
        } else {
            throw createAuthException(response.getStatus());
        }
    }

    public ApplicationException createAuthException(HttpStatus status) {
        return switch (status) {
            case BAD_REQUEST -> new ApplicationException(AuthErrorCode.UNSUPPORTED_TOKEN);
            case UNAUTHORIZED -> new ApplicationException(AuthErrorCode.EXPIRED_TOKEN);
            default -> new ApplicationException(AuthErrorCode.INVALID_TOKEN);
        };
    }

    @Override
    public UserResponse updateUserRole(String authorizationHeader, Role newRole) {
        UserResponse userInfo = getUserInfo(authorizationHeader);
        Long kakaoId = userInfo.kakaoId();
        User user = userRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));

        User updatedUser = User.builder()
                .userId(user.getUserId())
                .kakaoId(user.getKakaoId())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .updatedAt(new Date())
                .role(newRole)
                .build();

        updatedUser = userRepository.save(updatedUser);
        return UserResponse.from(updatedUser);
    }
}
