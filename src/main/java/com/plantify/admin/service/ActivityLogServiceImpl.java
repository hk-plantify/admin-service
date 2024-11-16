package com.plantify.admin.service;

import com.plantify.admin.domain.dto.request.ActivityLogRequest;
import com.plantify.admin.domain.dto.response.ActivityLogResponse;
import com.plantify.admin.domain.entity.ActivityLog;
import com.plantify.admin.domain.entity.TargetType;
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

    @Override
    public void recordActivity(ActivityLogRequest request) {
        ActivityLog activityLog = request.toEntity();
        activityLogRepository.save(activityLog);
    }

    @Override
    public List<ActivityLogResponse> getAllActivityLogs() {
        return activityLogRepository.findAll()
                .stream()
                .map(ActivityLogResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityLogResponse> getActivityLogs(TargetType targetType, Long targetId) {
        return activityLogRepository.findByTargetTypeAndTargetId(targetType, targetId)
                .stream()
                .map(ActivityLogResponse::from)
                .collect(Collectors.toList());
    }
}
