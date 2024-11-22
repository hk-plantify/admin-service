package com.plantify.admin.service;

import com.plantify.admin.util.UserInfoProvider;
import com.plantify.admin.domain.dto.request.ActivityHistoryRequest;
import com.plantify.admin.domain.dto.response.ActivityHistoryResponse;
import com.plantify.admin.domain.entity.ActivityHistory;
import com.plantify.admin.domain.entity.TargetType;
import com.plantify.admin.global.exception.ApplicationException;
import com.plantify.admin.global.exception.errorcode.ActivityHistoryErrorCode;
import com.plantify.admin.repository.ActivityHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityHistoryServiceImpl implements ActivityHistoryService {

    private final ActivityHistoryRepository activityLogRepository;
    private final UserInfoProvider userInfoProvider;


    @Override
    public void recordActivity(ActivityHistoryRequest request) {
        Long adminId = userInfoProvider.getUserInfo().userId();
        ActivityHistory activityLog = request.toEntity(adminId);
        activityLogRepository.save(activityLog);
    }

    @Override
    public List<ActivityHistoryResponse> getAllActivityLogs() {
        return activityLogRepository.findByIsDeletedFalse()
                .stream()
                .map(ActivityHistoryResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityHistoryResponse> getDeletedActivityLogs() {
        return activityLogRepository.findByIsDeletedTrue()
                .stream()
                .map(ActivityHistoryResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityHistoryResponse> getActivityLogs(TargetType targetType, Long targetId) {
        return activityLogRepository.findByTargetTypeAndTargetIdAndIsDeletedFalse(targetType, targetId)
                .stream()
                .map(ActivityHistoryResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityHistoryResponse getActivityLogById(Long activityLogId) {
        ActivityHistory activityLog = activityLogRepository.findById(activityLogId)
                .orElseThrow(() -> new ApplicationException(ActivityHistoryErrorCode.LOG_NOT_FOUND));
        return ActivityHistoryResponse.from(activityLog);
    }

    @Override
    public void deleteActivityLog(Long activityLogId) {
        Long adminId = userInfoProvider.getUserInfo().userId();
        ActivityHistory activityLog = activityLogRepository.findById(activityLogId)
                .orElseThrow(() -> new ApplicationException(ActivityHistoryErrorCode.LOG_NOT_FOUND));

        activityLog = activityLog.toBuilder()
                .isDeleted(true)
                .modifiedBy(adminId)
                .build();

        activityLogRepository.save(activityLog);
    }

    @Override
    public void restoreActivityLog(Long activityLogId) {
        Long adminId = userInfoProvider.getUserInfo().userId();
        ActivityHistory activityLog = activityLogRepository.findById(activityLogId)
                .orElseThrow(() -> new ApplicationException(ActivityHistoryErrorCode.LOG_NOT_FOUND));

        if (!activityLog.isDeleted()) {
            throw new ApplicationException(ActivityHistoryErrorCode.LOG_ALREADY_ACTIVE);
        }

        activityLog = activityLog.toBuilder()
                .isDeleted(false)
                .modifiedBy(adminId)
                .build();

        activityLogRepository.save(activityLog);
    }
}
