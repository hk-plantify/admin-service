package com.plantify.admin.controller;

import com.plantify.admin.domain.dto.request.ActivityHistoryRequest;
import com.plantify.admin.domain.dto.response.ActivityHistoryResponse;
import com.plantify.admin.domain.entity.TargetType;
import com.plantify.admin.global.response.ApiResponse;
import com.plantify.admin.service.ActivityHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/admin/activity-logs")
public class ActivityHistoryController {

    private final ActivityHistoryService activityHistoryService;

    @PostMapping
    public ApiResponse<Void> recordActivityLog(@RequestBody ActivityHistoryRequest request) {
        activityHistoryService.recordActivity(request);
        return ApiResponse.ok();
    }

    @GetMapping
    public ApiResponse<List<ActivityHistoryResponse>> getAllActivityLogs() {
        List<ActivityHistoryResponse> allActivityLogs = activityHistoryService.getAllActivityLogs();
        return ApiResponse.ok(allActivityLogs);
    }

    @GetMapping("/deleted")
    public ResponseEntity<ApiResponse<List<ActivityHistoryResponse>>> getDeletedActivityLogs() {
        List<ActivityHistoryResponse> deletedLogs = activityHistoryService.getDeletedActivityLogs();
        return ResponseEntity.ok(ApiResponse.ok(deletedLogs));
    }

    @GetMapping("/target")
    public ApiResponse<List<ActivityHistoryResponse>> getActivityLogsByTarget(
            @RequestParam TargetType targetType,
            @RequestParam Long targetId) {
        List<ActivityHistoryResponse> activityLogs = activityHistoryService.getActivityLogs(targetType, targetId);
        return ApiResponse.ok(activityLogs);
    }

    @GetMapping("/{activityLogId}")
    public ApiResponse<ActivityHistoryResponse> getActivityLogById(@PathVariable Long activityLogId) {
        ActivityHistoryResponse activityLog = activityHistoryService.getActivityLogById(activityLogId);
        return ApiResponse.ok(activityLog);
    }

    @DeleteMapping("/{activityLogId}")
    public ApiResponse<Void> deleteActivityLog(@PathVariable Long activityLogId) {
        activityHistoryService.deleteActivityLog(activityLogId);
        return ApiResponse.ok();
    }

    @PutMapping("/{activityLogId}/restore")
    public ApiResponse<Void> restoreActivityLog(@PathVariable Long activityLogId) {
        activityHistoryService.restoreActivityLog(activityLogId);
        return ApiResponse.ok();
    }
}
