package com.plantify.admin.service;

import com.plantify.admin.client.UserInfoProvider;
import com.plantify.admin.global.exception.ApplicationException;
import com.plantify.admin.global.exception.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserInfoProvider userInfoProvider;
    private final List<String> adminRoles;

    @Override
    public boolean validateAdminRole() {
        String role = userInfoProvider.getUserInfo().role();
        if (adminRoles.contains(role)) {
            return true;
        }
        throw new ApplicationException(UserErrorCode.USER_NOT_FOUND);
    }
}