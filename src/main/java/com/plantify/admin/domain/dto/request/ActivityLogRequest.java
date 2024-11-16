package com.plantify.admin.domain.dto.request;

import com.plantify.admin.domain.entity.ActionType;
import com.plantify.admin.domain.entity.ActivityLog;
import com.plantify.admin.domain.entity.TargetType;
import lombok.Builder;

public record ActivityLogRequest(
        TargetType targetType,
        Long targetId,
        ActionType actionType,
        Long userId,
        boolean isDeleted,
        Long modifiedBy
) {

    @Builder
    public ActivityLogRequest {}

    public ActivityLog toEntity(Long adminId) {
        return ActivityLog.builder()
                .targetType(targetType)
                .targetId(targetId)
                .actionType(actionType)
                .userId(adminId)
                .isDeleted(isDeleted)
                .modifiedBy(modifiedBy)
                .build();
    }
}
