package com.plantify.admin.domain.dto.request;

import com.plantify.admin.domain.entity.ActionType;
import com.plantify.admin.domain.entity.ActivityLog;
import com.plantify.admin.domain.entity.TargetType;
import com.plantify.admin.global.exception.ApplicationException;
import lombok.Builder;

public record ActivityLogRequest(
        String targetType,
        Long targetId,
        String actionType,
        Long userId,
        boolean isDeleted,
        Long modifiedBy
) {

    @Builder
    public ActivityLogRequest {}

    public ActivityLog toEntity(Long adminId) {
        return ActivityLog.builder()
                .targetType(toTargetType())
                .targetId(targetId)
                .actionType(toActionType())
                .userId(adminId)
                .isDeleted(isDeleted)
                .modifiedBy(modifiedBy)
                .build();
    }

    public TargetType toTargetType() {
        return TargetType.valueOf(targetType.toUpperCase());
    }

    public ActionType toActionType() {
        return ActionType.valueOf(actionType.toUpperCase());
    }
}
