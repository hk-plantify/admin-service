package com.plantify.admin.service;

import com.plantify.admin.domain.dto.request.ActivityLogRequest;
import com.plantify.admin.domain.dto.response.ActivityLogResponse;
import com.plantify.admin.domain.entity.TargetType;

import java.util.List;

public interface ActivityLogService {

    void recordActivity(ActivityLogRequest request);
    List<ActivityLogResponse> getAllActivityLogs();
    List<ActivityLogResponse> getActivityLogs(TargetType targetType, Long targetId);
    ActivityLogResponse getActivityLogById(Long activityLogId);
    void deleteActivityLog(Long activityLogId);
}
