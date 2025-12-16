package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QLocation;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> searchReview(
            Predicate predicate
    ){

        QReview review = QReview.review;
        QLocation location = QLocation.location;
        QStore store = QStore.store;

        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(location).on(location.id.eq(store.location.id))
                .where(predicate)
                .fetch();
    }

    @Override
    public Page<Review> findMyReviews(Predicate predicate, Pageable pageable) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        List<Review> content = queryFactory
                .selectFrom(review)
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
