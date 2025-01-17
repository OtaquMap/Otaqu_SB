package com.otakumap.domain.reviews.repository;

import com.otakumap.domain.event_review.entity.EventReview;
import com.otakumap.domain.event_review.entity.QEventReview;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.entity.QPlaceReview;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.SearchHandler;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewsRepositoryImpl implements ReviewsRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Object> getReviewsByKeyword(String keyword) {
        QEventReview eventReview = QEventReview.eventReview;
        QPlaceReview placeReview = QPlaceReview.placeReview;

        BooleanBuilder eventCondition = new BooleanBuilder();
        eventCondition.and(eventReview.title.containsIgnoreCase(keyword)
                .or(eventReview.content.containsIgnoreCase(keyword)));

        BooleanBuilder placeCondition = new BooleanBuilder();
        placeCondition.and(placeReview.title.containsIgnoreCase(keyword)
                .or(placeReview.content.containsIgnoreCase(keyword)));

        List<EventReview> eventReviews = queryFactory.selectFrom(eventReview)
                .where(eventCondition)
                .fetch();

        List<PlaceReview> placeReviews = queryFactory.selectFrom(placeReview)
                .where(placeCondition)
                .fetch();

        List<Object> combinedResults = new ArrayList<>();
        combinedResults.addAll(eventReviews);
        combinedResults.addAll(placeReviews);

        if (combinedResults.isEmpty()) {
            throw new SearchHandler(ErrorStatus.REVIEW_SEARCH_NOT_FOUND);
        }

        return combinedResults;
    }
}
