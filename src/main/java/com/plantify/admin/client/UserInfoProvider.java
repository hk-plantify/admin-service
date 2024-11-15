package com.plantify.admin.client;

import com.plantify.admin.domain.dto.response.AuthUserResponse;
import com.plantify.admin.global.exception.ApplicationException;
import com.plantify.admin.global.exception.errorcode.AuthErrorCode;
import com.plantify.admin.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoProvider {

    private final AuthServiceClient authServiceClient;

    public AuthUserResponse getUserInfo(String authenticationHeader) {
        ApiResponse<AuthUserResponse> response = authServiceClient.getUserInfo(authenticationHeader);
        if (response.getStatus() == HttpStatus.OK) {
            return response.getData();
        } else {
            throw switch (response.getStatus()) {
                case UNAUTHORIZED -> new ApplicationException(AuthErrorCode.INVALID_TOKEN);
                case FORBIDDEN -> new ApplicationException(AuthErrorCode.ACCESS_TOKEN_NULL);
                default -> new ApplicationException(AuthErrorCode.UNSUPPORTED_TOKEN);
            };
        }
    }
}
