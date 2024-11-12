package com.plantify.admin.domain.dto.response;

import com.plantify.admin.domain.entity.User;

public record UserResponse(Long kakaoId, String role) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getKakaoId(),
                user.getRole().toString()
        );
    }
}
