package com.plantify.admin.controller.client;

import com.plantify.admin.domain.dto.response.UserResponse;
import com.plantify.admin.global.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "${auth.service.url}")
public interface AuthServiceClient {

    @PostMapping("/v1/auth/refresh")
    ApiResponse<String> refreshAccessToken(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/v1/auth/validate-token")
    ApiResponse<UserResponse> getUserInfo(@RequestHeader("Authorization") String authorizationHeader);
}