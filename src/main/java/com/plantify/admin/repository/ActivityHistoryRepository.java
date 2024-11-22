package com.plantify.admin.repository;

import com.plantify.admin.domain.entity.ActivityHistory;
import com.plantify.admin.domain.entity.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory, Long> {

    List<ActivityHistory> findByIsDeletedFalse();
    List<ActivityHistory> findByIsDeletedTrue();
    List<ActivityHistory> findByTargetTypeAndTargetIdAndIsDeletedFalse(TargetType targetType, Long targetId);
}
