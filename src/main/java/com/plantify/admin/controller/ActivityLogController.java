package com.plantify.admin.controller;

import com.plantify.admin.domain.dto.request.ActivityLogRequest;
import com.plantify.admin.domain.dto.response.ActivityLogResponse;
import com.plantify.admin.domain.entity.TargetType;
import com.plantify.admin.global.response.ApiResponse;
import com.plantify.admin.service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/admin/activity-logs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> recordActivityLog(@RequestBody ActivityLogRequest request) {
        activityLogService.recordActivity(request);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ActivityLogResponse>>> getAllActivityLogs() {
        List<ActivityLogResponse> allActivityLogs = activityLogService.getAllActivityLogs();
        return ResponseEntity.ok(ApiResponse.ok(allActivityLogs));
    }

    @GetMapping("/target")
    public ResponseEntity<ApiResponse<List<ActivityLogResponse>>> getActivityLogsByTarget(
            @RequestParam TargetType targetType,
            @RequestParam Long targetId) {
        List<ActivityLogResponse> activityLogs = activityLogService.getActivityLogs(targetType, targetId);
        return ResponseEntity.ok(ApiResponse.ok(activityLogs));
    }

    @GetMapping("/{activityLogId}")
    public ResponseEntity<ApiResponse<ActivityLogResponse>> getActivityLogById(@PathVariable Long activityLogId) {
        ActivityLogResponse activityLog = activityLogService.getActivityLogById(activityLogId);
        return ResponseEntity.ok(ApiResponse.ok(activityLog));
    }

    @DeleteMapping("/{activityLogId}")
    public ResponseEntity<ApiResponse<Void>> deleteActivityLog(@PathVariable Long activityLogId) {
        activityLogService.deleteActivityLog(activityLogId);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
