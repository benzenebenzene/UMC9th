package com.example.umc9th.domain.store.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LocationErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REGION404",
            "지역을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}