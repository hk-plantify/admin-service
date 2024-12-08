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
    public ResponseEntity<ApiResponse<Void>> recordActivityLog(@RequestBody ActivityHistoryRequest request) {
        activityHistoryService.recordActivity(request);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ActivityHistoryResponse>>> getAllActivityLogs() {
        List<ActivityHistoryResponse> allActivityLogs = activityHistoryService.getAllActivityLogs();
        return ResponseEntity.ok(ApiResponse.ok(allActivityLogs));
    }

    @GetMapping("/deleted")
    public ResponseEntity<ApiResponse<List<ActivityHistoryResponse>>> getDeletedActivityLogs() {
        List<ActivityHistoryResponse> deletedLogs = activityHistoryService.getDeletedActivityLogs();
        return ResponseEntity.ok(ApiResponse.ok(deletedLogs));
    }

    @GetMapping("/target")
    public ResponseEntity<ApiResponse<List<ActivityHistoryResponse>>> getActivityLogsByTarget(
            @RequestParam TargetType targetType,
            @RequestParam Long targetId) {
        List<ActivityHistoryResponse> activityLogs = activityHistoryService.getActivityLogs(targetType, targetId);
        return ResponseEntity.ok(ApiResponse.ok(activityLogs));
    }

    @GetMapping("/{activityLogId}")
    public ResponseEntity<ApiResponse<ActivityHistoryResponse>> getActivityLogById(@PathVariable Long activityLogId) {
        ActivityHistoryResponse activityLog = activityHistoryService.getActivityLogById(activityLogId);
        return ResponseEntity.ok(ApiResponse.ok(activityLog));
    }

    @DeleteMapping("/{activityLogId}")
    public ResponseEntity<ApiResponse<Void>> deleteActivityLog(@PathVariable Long activityLogId) {
        activityHistoryService.deleteActivityLog(activityLogId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PutMapping("/{activityLogId}/restore")
    public ResponseEntity<ApiResponse<Void>> restoreActivityLog(@PathVariable Long activityLogId) {
        activityHistoryService.restoreActivityLog(activityLogId);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
