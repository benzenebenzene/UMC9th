package com.example.umc9th.domain.review.converter;


import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    public ReviewResDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResDTO.ReviewDTO.builder()
                .id(review.getId())
                .memberId(review.getId())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .build();
    }

    public ReviewResDTO.ReviewListDTO toReviewListDTO(List<Review> reviews) {
        List<ReviewResDTO.ReviewDTO> reviewDTOs = reviews.stream()
                .map(this::toReviewDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListDTO.builder()
                .reviews(reviewDTOs)
                .totalCount(reviewDTOs.size())
                .build();
    }

}
