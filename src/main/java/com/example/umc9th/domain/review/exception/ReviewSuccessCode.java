package com.example.umc9th.domain.review.exception;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_RETRIEVED(HttpStatus.OK, "REVIEW_200", "Reviews retrieved successfully");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
