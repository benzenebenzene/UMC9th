package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    //리뷰 검색
    @GetMapping("/reviews/search")
    public ApiResponse<ReviewResDTO.ReviewListDTO> searchReview(
            @RequestParam String query,
            @RequestParam String type
    ){

        List<Review> reviews = reviewQueryService.searchReview(query, type);
        ReviewResDTO.ReviewListDTO reviewListDTO = ReviewConverter.toReviewListDTO(reviews);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewListDTO);
    }

    //나의 리뷰 조회
    @GetMapping("/reviews/my")
    public ApiResponse<ReviewResDTO.ReviewListDTO> findMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer starRange
    ){

        List<Review> reviews = reviewQueryService.findMyReviews(memberId, storeName, starRange);
        ReviewResDTO.ReviewListDTO reviewListDTO = ReviewConverter.toReviewListDTO(reviews);
        return ApiResponse.onSuccess(ReviewSuccessCode.FOUND, reviewListDTO);
    }

    //가게에 리뷰 추가
    @PostMapping("/shops/{shopId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewResponseDTO> createReview(
            @Valid @RequestBody ReviewReqDTO.ReviewRequestDTO dto
    ) {

        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, reviewCommandService.createReview(dto));
    }


    // 가게의 리뷰 목록 조회
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 벤젠 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })

    // 가게의 리뷰 목록 조회
    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page
    ){

        ReviewSuccessCode code = ReviewSuccessCode.FOUND;
        return ApiResponse.onSuccess(code, reviewQueryService.findReview(storeName, page));
    }
}
