package com.example.umc9th.domain.review.dto.res;


import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewDTO(
            Long id,
            Long memberId,
            String storeName,
            Float star,
            String content
    ){}

    @Builder
    public record ReviewListDTO(
            List<ReviewDTO> reviews,
            int totalCount
    ) {}
}
