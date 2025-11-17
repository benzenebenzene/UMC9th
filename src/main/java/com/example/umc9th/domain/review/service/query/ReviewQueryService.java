package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryService {

    List<Review> searchReview(String query, String type);
    List<Review> findMyReviews(Long memberId, String storeName, Integer starRange);

}
