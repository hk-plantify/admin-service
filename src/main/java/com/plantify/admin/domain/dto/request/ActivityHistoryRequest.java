package com.plantify.admin.domain.dto.request;

import com.plantify.admin.domain.entity.ActionType;
import com.plantify.admin.domain.entity.ActivityHistory;
import com.plantify.admin.domain.entity.TargetType;
import lombok.Builder;

public record ActivityHistoryRequest(
        String targetType,
        Long targetId,
        String actionType,
        Long userId,
        boolean isDeleted,
        Long modifiedBy
) {

    @Builder
    public ActivityHistoryRequest {}

    public ActivityHistory toEntity(Long adminId) {
        return ActivityHistory.builder()
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
