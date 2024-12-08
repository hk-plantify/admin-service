package com.plantify.admin.domain.dto.request;

import com.plantify.admin.domain.entity.Role;
import com.plantify.admin.domain.entity.User;

public record UserRequest(
        String username,
        Role role
) {

    public User updatedUser(User user) {
        return user.toBuilder()
                .username(this.username())
                .role(this.role())
                .build();
    }

}
