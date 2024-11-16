package com.plantify.admin.service;

import com.plantify.admin.domain.entity.ActionType;
import com.plantify.admin.domain.entity.TargetType;

public interface UserInternalService {

    void recordActivityLog(TargetType targetType, Long targetId, ActionType actionType, Long adminId);
}
