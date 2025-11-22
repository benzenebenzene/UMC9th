package com.example.umc9th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PageErrorCode implements BaseErrorCode{
    INVALID_PAGE(HttpStatus.BAD_REQUEST,
            "COMMON400",
            "페이지 번호는 1 이상이어야 합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
