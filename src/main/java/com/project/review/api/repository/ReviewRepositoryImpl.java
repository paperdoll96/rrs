package com.project.review.api.repository;


import com.project.review.api.model.QReviewEntity;
import com.project.review.api.model.ReviewEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public Double getAvgScoreByRestaurantId(Long restaurantId) {
        Double avgScore = queryFactory.select(QReviewEntity.reviewEntity.score.avg())
                .from(QReviewEntity.reviewEntity)
                .where(QReviewEntity.reviewEntity.restaurantId.eq(restaurantId))
                .fetchFirst();
        return avgScore != null ? avgScore : 0.0; // 평균이 없을 때 0.0 반환
    }

    @Override
    public Slice<ReviewEntity> findSliceByRestaurantId(Long restaurantId, Pageable page) {
        List<ReviewEntity> reviews = queryFactory.select(QReviewEntity.reviewEntity)
                .from(QReviewEntity.reviewEntity)
                .where(QReviewEntity.reviewEntity.restaurantId.eq(restaurantId))
                .offset((long) page.getPageNumber() * page.getPageSize())
                .limit(page.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(
                reviews.stream().limit(page.getPageSize()).toList(),
                page,
                reviews.size() > page.getPageSize()

        );
    }
}
