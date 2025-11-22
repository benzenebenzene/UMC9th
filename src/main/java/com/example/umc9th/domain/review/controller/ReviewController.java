package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewConverter reviewConverter;
    private final ReviewCommandService reviewCommandService;

    //리뷰 검색
    @GetMapping("/reviews/search")
    public ApiResponse<ReviewResDTO.ReviewListDTO> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){

        List<Review> reviews = reviewQueryService.searchReview(query, type);
        ReviewResDTO.ReviewListDTO reviewListDTO = reviewConverter.toReviewListDTO(reviews);
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, reviewListDTO);
    }

    //나의 리뷰 조회
    @GetMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.ReviewListDTO> findMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer starRange
    ){

        List<Review> reviews = reviewQueryService.findMyReviews(memberId, storeName, starRange);
        ReviewResDTO.ReviewListDTO reviewListDTO = reviewConverter.toReviewListDTO(reviews);
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, reviewListDTO);
    }

    //가게에 리뷰 추가
    @PostMapping("/shops/{shopId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewResponseDTO> createReview(
            @Valid @RequestBody ReviewReqDTO.ReviewRequestDTO dto
    ) {

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, reviewCommandService.createReview(dto));
    }
}
