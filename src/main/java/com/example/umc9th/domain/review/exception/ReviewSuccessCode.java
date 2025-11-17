package com.example.umc9th.domain.review.exception;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "REVIEW200",
            "리뷰 정보를 성공적으로 조회했습니다."),
    CREATED(HttpStatus.CREATED,
            "REVIEW201",
            "리뷰 정보를 성공적으로 생성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
