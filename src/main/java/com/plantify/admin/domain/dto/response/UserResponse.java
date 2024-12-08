package com.plantify.admin.domain.dto.response;

import com.plantify.admin.domain.entity.Role;
import com.plantify.admin.domain.entity.User;

import java.time.LocalDateTime;

public record UserResponse(
        Long userId,
        Long kakaoId,
        String username,
        Role role,
        Long modifiedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getKakaoId(),
                user.getUsername(),
                user.getRole(),
                user.getModifiedBy(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
