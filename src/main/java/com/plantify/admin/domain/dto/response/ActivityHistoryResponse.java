package com.plantify.admin.domain.dto.response;

import com.plantify.admin.domain.entity.ActionType;
import com.plantify.admin.domain.entity.ActivityHistory;
import com.plantify.admin.domain.entity.TargetType;

import java.time.LocalDateTime;

public record ActivityHistoryResponse(
        Long activityHistoryId,
        TargetType targetType,
        Long targetId,
        ActionType actionType,
        Long userId,
        boolean isDeleted,
        Long modifiedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ActivityHistoryResponse from(ActivityHistory activityHistory) {
        return new ActivityHistoryResponse(
                activityHistory.getActivityHistoryId(),
                activityHistory.getTargetType(),
                activityHistory.getTargetId(),
                activityHistory.getActionType(),
                activityHistory.getUserId(),
                activityHistory.isDeleted(),
                activityHistory.getModifiedBy(),
                activityHistory.getCreatedAt(),
                activityHistory.getUpdatedAt()
        );
    }
}
