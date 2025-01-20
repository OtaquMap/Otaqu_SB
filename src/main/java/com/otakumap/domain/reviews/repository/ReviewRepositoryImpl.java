package com.otakumap.domain.reviews.repository;

import com.otakumap.domain.animation.entity.QAnimation;
import com.otakumap.domain.event.entity.QEvent;
import com.otakumap.domain.event_review.entity.EventReview;
import com.otakumap.domain.event_review.entity.QEventReview;
import com.otakumap.domain.mapping.entity.QEventAnimation;
import com.otakumap.domain.mapping.entity.QPlaceAnimation;
import com.otakumap.domain.place.entity.QPlace;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.entity.QPlaceReview;
import com.otakumap.domain.reviews.converter.ReviewConverter;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.SearchHandler;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewResponseDTO.SearchedReviewPreViewDTO> getReviewsByKeyword(String keyword, int page, int size, String sort) {
        QEventReview eventReview = QEventReview.eventReview;
        QPlaceReview placeReview = QPlaceReview.placeReview;
        QEventAnimation eventAnimation = QEventAnimation.eventAnimation;
        QPlaceAnimation placeAnimation = QPlaceAnimation.placeAnimation;
        QAnimation animation = QAnimation.animation;

        // 이벤트 리뷰 검색 : EventReview 제목, 내용, 또는 연관된 애니메이션 이름
        BooleanBuilder eventCondition = new BooleanBuilder();
        eventCondition.or(eventReview.title.containsIgnoreCase(keyword)
                .or(eventReview.content.containsIgnoreCase(keyword))
                .or(eventAnimation.animation.name.containsIgnoreCase(keyword))
        );

        // 장소 리뷰 검색 : PlaceReview 제목, 내용, 또는 연관된 애니메이션 이름
        BooleanBuilder placeCondition = new BooleanBuilder();
        placeCondition.or(placeReview.title.containsIgnoreCase(keyword)
                .or(placeReview.content.containsIgnoreCase(keyword))
                .or(placeAnimation.animation.name.containsIgnoreCase(keyword))
        );

        List<EventReview> eventReviews = queryFactory.selectFrom(eventReview)
                .leftJoin(eventReview.event, QEvent.event)
                .leftJoin(QEvent.event.eventAnimationList, eventAnimation)
                .leftJoin(eventAnimation.animation, animation)
                .where(eventCondition)
                .fetch();

        List<PlaceReview> placeReviews = queryFactory.selectFrom(placeReview)
                .leftJoin(placeReview.place, QPlace.place)
                .leftJoin(QPlace.place.placeAnimationList, placeAnimation)
                .leftJoin(placeAnimation.animation, animation)
                .where(placeCondition)
                .fetch();

        List<ReviewResponseDTO.SearchedReviewPreViewDTO> searchedReviews = new ArrayList<>();

        for(EventReview review : eventReviews) {
            searchedReviews.add(ReviewConverter.toSearchedEventReviewPreviewDTO(review));
        }

        for(PlaceReview review : placeReviews) {
            searchedReviews.add(ReviewConverter.toSearchedPlaceReviewPreviewDTO(review));
        }

        if (searchedReviews.isEmpty()) {
            throw new SearchHandler(ErrorStatus.REVIEW_SEARCH_NOT_FOUND);
        }

        // 정렬 (최신순, 조회수)
        if ("views".equalsIgnoreCase(sort)) {
            searchedReviews.sort(Comparator.comparing(ReviewResponseDTO.SearchedReviewPreViewDTO::getView).reversed()
                    .thenComparing(ReviewResponseDTO.SearchedReviewPreViewDTO::getCreatedAt) // 조회수가 같으면 최신순을 기준으로
            );
        } else {
            searchedReviews.sort(Comparator.comparing(ReviewResponseDTO.SearchedReviewPreViewDTO::getCreatedAt).reversed());
        }

        // 페이징
        int start = page * size;
        int end = Math.min(start + size, searchedReviews.size());
        if (start > end) {
            return new PageImpl<>(new ArrayList<>(), PageRequest.of(page, size), searchedReviews.size());
        }

        return new PageImpl<>(searchedReviews.subList(start, end), PageRequest.of(page, size), searchedReviews.size());
    }
}
