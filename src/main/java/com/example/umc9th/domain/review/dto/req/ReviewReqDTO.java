package com.example.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {
    public record ReviewRequestDTO(
            Long storeId,
            @NotNull
            @Max(value = 5)
            @Min(value = 0)
            Long star,
            String content
    ){}
}
