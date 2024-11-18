package com.plantify.admin.service;

import com.plantify.admin.client.UserInfoProvider;
import com.plantify.admin.domain.dto.request.ActivityLogRequest;
import com.plantify.admin.domain.dto.response.ActivityLogResponse;
import com.plantify.admin.domain.entity.ActivityLog;
import com.plantify.admin.domain.entity.TargetType;
import com.plantify.admin.global.exception.ApplicationException;
import com.plantify.admin.global.exception.errorcode.ActivityLogErrorCode;
import com.plantify.admin.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final UserInfoProvider userInfoProvider;


    @Override
    public void recordActivity(ActivityLogRequest request) {
        Long adminId = userInfoProvider.getUserInfo().userId();
        ActivityLog activityLog = request.toEntity(adminId);
        activityLogRepository.save(activityLog);
    }

    @Override
    public List<ActivityLogResponse> getAllActivityLogs() {
        return activityLogRepository.findByIsDeletedFalse()
                .stream()
                .map(ActivityLogResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityLogResponse> getDeletedActivityLogs() {
        return activityLogRepository.findByIsDeletedTrue()
                .stream()
                .map(ActivityLogResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityLogResponse> getActivityLogs(TargetType targetType, Long targetId) {
        return activityLogRepository.findByTargetTypeAndTargetIdAndIsDeletedFalse(targetType, targetId)
                .stream()
                .map(ActivityLogResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityLogResponse getActivityLogById(Long activityLogId) {
        ActivityLog activityLog = activityLogRepository.findById(activityLogId)
                .orElseThrow(() -> new ApplicationException(ActivityLogErrorCode.LOG_NOT_FOUND));
        return ActivityLogResponse.from(activityLog);
    }

    @Override
    public void deleteActivityLog(Long activityLogId) {
        Long adminId = userInfoProvider.getUserInfo().userId();
        ActivityLog activityLog = activityLogRepository.findById(activityLogId)
                .orElseThrow(() -> new ApplicationException(ActivityLogErrorCode.LOG_NOT_FOUND));

        activityLog = activityLog.toBuilder()
                .isDeleted(true)
                .modifiedBy(adminId)
                .build();

        activityLogRepository.save(activityLog);
    }

    @Override
    public void restoreActivityLog(Long activityLogId) {
        Long adminId = userInfoProvider.getUserInfo().userId();
        ActivityLog activityLog = activityLogRepository.findById(activityLogId)
                .orElseThrow(() -> new ApplicationException(ActivityLogErrorCode.LOG_NOT_FOUND));

        if (!activityLog.isDeleted()) {
            throw new ApplicationException(ActivityLogErrorCode.LOG_ALREADY_ACTIVE);
        }

        activityLog = activityLog.toBuilder()
                .isDeleted(false)
                .modifiedBy(adminId)
                .build();

        activityLogRepository.save(activityLog);
    }
}
