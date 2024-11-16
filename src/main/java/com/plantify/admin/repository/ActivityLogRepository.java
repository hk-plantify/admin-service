package com.plantify.admin.repository;

import com.plantify.admin.domain.entity.ActivityLog;
import com.plantify.admin.domain.entity.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findByIsDeletedFalse();
    List<ActivityLog> findByTargetTypeAndTargetIdAndIsDeletedFalse(TargetType targetType, Long targetId);
}
