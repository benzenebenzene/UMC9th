package com.example.umc9th.domain.review.converter;


import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewResponseDTO toReviewDTO(
            Store store, Review review
    ){
        return ReviewResDTO.ReviewResponseDTO.builder()
                .reviewId(review.getReviewId())
                .storeName(review.getStore().getName())
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

    public static ReviewResDTO.ReviewListDTO toReviewListDTO(List<Review> reviews) {
        List<ReviewResDTO.ReviewResponseDTO> reviewDTOs = reviews.stream()
                .map(review -> toReviewDTO(review.getStore(), review))
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewListDTO.builder()
                .reviews(reviewDTOs)
                .totalCount(reviewDTOs.size())
                .build();
    }
    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getStar())
                .body(review.getReviewContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}
