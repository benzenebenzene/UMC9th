package com.example.umc9th.domain.review.dto.res;


import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewResponseDTO(
            Long id,
            Long reviewId,
            Long storeId,
            String storeName,
            Long star,
            String content
    ){}

    @Builder
    public record ReviewListDTO(
            List<ReviewResponseDTO> reviews,
            int totalCount
    ) {}

}
