package com.plantify.admin.global.exception.errorcode;

import com.plantify.admin.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ActivityLogErrorCode implements ErrorCode {

    LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "Activity log not found."),
    INVALID_LOG_REQUEST(HttpStatus.BAD_REQUEST, "Invalid activity log request."),
    LOG_ALREADY_ACTIVE(HttpStatus.BAD_REQUEST, "Activity log is already active"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    @Override
    public String getMessage() {
        return "";
    }
}

