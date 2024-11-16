package com.plantify.admin.domain.dto.request;

import com.plantify.admin.domain.entity.ActionType;
import com.plantify.admin.domain.entity.ActivityLog;
import com.plantify.admin.domain.entity.TargetType;

public record ActivityLogRequest(
        TargetType targetType,
        Long targetId,
        ActionType actionType,
        Long userId
) {

    public ActivityLog toEntity() {
        return ActivityLog.builder()
                .targetType(targetType)
                .targetId(targetId)
                .actionType(actionType)
                .userId(userId)
                .build();
    }
}
