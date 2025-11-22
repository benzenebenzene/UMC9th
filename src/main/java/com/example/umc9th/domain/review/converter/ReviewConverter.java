package com.example.umc9th.domain.review.converter;


import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewResponseDTO toReviewDTO(
            Store store, Review review
    ){
        return ReviewResDTO.ReviewResponseDTO.builder()
                .reviewId(review.getReviewId())
                .storeName(review.getStore().getStoreName())
                .star(review.getStar())
                .content(review.getReviewContent())
                .build();
    }

    public static Review toReview(
            Store store,
            ReviewReqDTO.ReviewRequestDTO dto
    ){
        return Review.builder()
                .reviewContent(dto.content())
                .star(dto.star())
                .store(store)
                .build();
    }

    public ReviewResDTO.ReviewListDTO toReviewListDTO(List<Review> reviews) {
        List<ReviewResDTO.ReviewResponseDTO> reviewDTOs = reviews.stream()
                .map(review -> toReviewDTO(review.getStore(), review))
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListDTO.builder()
                .reviews(reviewDTOs)
                .totalCount(reviewDTOs.size())
                .build();
    }
}
