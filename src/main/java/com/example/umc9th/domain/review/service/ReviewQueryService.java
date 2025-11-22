package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

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

    // 검색 API
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
