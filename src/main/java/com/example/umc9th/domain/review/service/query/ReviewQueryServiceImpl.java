package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private ReviewRepository reviewRepository;

    public List<Review> searchReview(String query, String type) {

        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        if (type.equals("location")) {
            builder.and(review.store.location.name.contains(query));
        }

        if (type.equals("star")) {
            builder.and(review.star.goe(Float.parseFloat(query)));
        }

        if (type.equals("both")) {
            String firstQuery = query.split("&")[0];
            String secondQuery = query.split("&")[1];

            builder.and(review.store.location.name.contains(firstQuery));
            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
        }

        return reviewRepository.searchReview(builder);
    }

    public List<Review> findMyReviews(Long memberId, String storeName, Integer starRange) {

        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        // 가게명 필터
        if (storeName != null) {
            builder.and(review.store.name.eq(storeName));
        }

        // 별점 필터
        if (starRange != null) {
            builder.and(review.star.goe(starRange).and(review.star.lt(starRange + 1.0)));
        }

        return reviewRepository.findMyReviews(builder);
    }
}
