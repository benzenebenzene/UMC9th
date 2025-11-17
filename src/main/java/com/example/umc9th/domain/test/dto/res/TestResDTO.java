package com.example.umc9th.domain.test.dto.res;

import lombok.Builder;

public class TestResDTO {

    @Builder
    public record Testing (
            String testString
    ){}

    @Builder
    public record Exception (
            String testString
    ){}
}
