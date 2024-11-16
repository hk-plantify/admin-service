package com.plantify.admin.global.exception.errorcode;

import com.plantify.admin.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    USER_INFO_NULL(HttpStatus.NOT_FOUND, "사용자 정보가 비어 있습니다."),
    INVALID_USERNAME(HttpStatus.BAD_REQUEST, "유효하지 않은 사용자 이름입니다."),
    USER_ACCESS_DENIED(HttpStatus.FORBIDDEN, "유저에 대한 접근 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
