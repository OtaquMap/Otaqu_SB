package com.otakumap.domain.place_review.repository;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.animation.entity.QAnimation;
import com.otakumap.domain.mapping.QPlaceAnimation;
import com.otakumap.domain.place.entity.QPlace;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.entity.QPlaceReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PlaceReviewRepositoryImpl implements PlaceReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Map<Animation, List<PlaceReview>> findReviewsByPlaceWithAnimation(Long placeId, int page, int size, String sort) {

        QPlaceReview placeReview = QPlaceReview.placeReview;
        QAnimation animation = QAnimation.animation;
        QPlaceAnimation placeAnimation = QPlaceAnimation.placeAnimation;
        QPlace place = QPlace.place;


        List<PlaceReview> reviews = queryFactory.selectFrom(placeReview)
                .join(placeReview.place, place) // PlaceReview에서 Place로 조인
                .join(place.placeAnimationList, placeAnimation) // Place에서 PlaceAnimation으로 조인
                .join(placeAnimation.animation, animation) // PlaceAnimation에서 Animation으로 조인
                .where(placeReview.place.id.eq(placeId))
                .fetch();

         // Animation을 기준으로 그룹화
        return reviews.stream()
                .collect(Collectors.groupingBy(review -> review.getPlaceAnimation().getAnimation()));
    }
}
