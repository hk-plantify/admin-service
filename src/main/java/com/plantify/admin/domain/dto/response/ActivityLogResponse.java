package com.plantify.admin.domain.dto.response;

import com.plantify.admin.domain.entity.ActionType;
import com.plantify.admin.domain.entity.ActivityLog;
import com.plantify.admin.domain.entity.TargetType;

import java.time.LocalDateTime;

public record ActivityLogResponse(
        Long activityLogId,
        TargetType targetType,
        Long targetId,
        ActionType actionType,
        Long userId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ActivityLogResponse from(ActivityLog activityLog) {
        return new ActivityLogResponse(
                activityLog.getActivityLogId(),
                activityLog.getTargetType(),
                activityLog.getTargetId(),
                activityLog.getActionType(),
                activityLog.getUserId(),
                activityLog.getCreatedAt(),
                activityLog.getUpdatedAt()
        );
    }
}
