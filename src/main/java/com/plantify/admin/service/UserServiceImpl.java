package com.plantify.admin.service;

import com.plantify.admin.util.UserInfoProvider;
import com.plantify.admin.domain.dto.request.ActivityHistoryRequest;
import com.plantify.admin.domain.dto.request.UserRequest;
import com.plantify.admin.domain.dto.response.UserResponse;
import com.plantify.admin.domain.entity.ActionType;
import com.plantify.admin.domain.entity.TargetType;
import com.plantify.admin.domain.entity.User;
import com.plantify.admin.global.exception.ApplicationException;
import com.plantify.admin.global.exception.errorcode.UserErrorCode;
import com.plantify.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserInternalService {

    private final UserRepository userRepository;
    private final ActivityHistoryService activityLogService;
    private final UserInfoProvider userInfoProvider;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        Long adminId = userInfoProvider.getUserInfo().userId();
        recordActivityLog(TargetType.USER, userId, ActionType.VIEW, adminId);

        return UserResponse.from(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        Long adminId = userInfoProvider.getUserInfo().userId();

        User updatedUser = user.toBuilder()
                .username(request.username())
                .role(request.role())
                .modifiedBy(adminId)
                .build();
        User savedUser = userRepository.save(updatedUser);
        recordActivityLog(TargetType.USER, userId, ActionType.UPDATE, adminId);

        return UserResponse.from(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(UserErrorCode.USER_NOT_FOUND));
        Long adminId = userInfoProvider.getUserInfo().userId();

        userRepository.delete(user);
        recordActivityLog(TargetType.USER, userId, ActionType.DELETE, adminId);
    }

    @Override
    public void recordActivityLog(TargetType targetType, Long targetId, ActionType actionType, Long adminId) {
        activityLogService.recordActivity(ActivityHistoryRequest.builder()
                .targetType(targetType.name())
                .targetId(targetId)
                .actionType(actionType.name())
                .userId(adminId)
                .build());
    }
}
