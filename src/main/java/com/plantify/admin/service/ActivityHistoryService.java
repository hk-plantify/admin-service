package com.plantify.admin.service;

import com.plantify.admin.domain.dto.request.ActivityHistoryRequest;
import com.plantify.admin.domain.dto.response.ActivityHistoryResponse;
import com.plantify.admin.domain.entity.TargetType;

import java.util.List;

public interface ActivityHistoryService {

    void recordActivity(ActivityHistoryRequest request);
    List<ActivityHistoryResponse> getAllActivityLogs();
    List<ActivityHistoryResponse> getDeletedActivityLogs();
    List<ActivityHistoryResponse> getActivityLogs(TargetType targetType, Long targetId);
    ActivityHistoryResponse getActivityLogById(Long activityLogId);
    void deleteActivityLog(Long activityLogId);
    void restoreActivityLog(Long activityLogId);
}
