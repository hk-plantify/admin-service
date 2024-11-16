package com.plantify.admin.domain.dto.request;

import com.plantify.admin.domain.entity.Role;

public record UserRequest(
        String username,
        Role role,
        Long modifiedBy
) {

}
