package com.otakumap.domain.search.repository;


import com.otakumap.domain.animation.entity.QAnimation;
import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event.entity.QEvent;
import com.otakumap.domain.event.entity.enums.EventStatus;
import com.otakumap.domain.mapping.QEventAnimation;
import com.otakumap.domain.mapping.QPlaceAnimation;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.entity.QPlace;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryCustomImpl implements SearchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Event> searchEventsByKeyword(String keyword) {

        QEvent qEvent = QEvent.event;
        QEventAnimation qEventAnimation = QEventAnimation.eventAnimation;
        QAnimation qAnimation = QAnimation.animation;

        BooleanBuilder condition = new BooleanBuilder();
        condition.or(qEvent.title.containsIgnoreCase(keyword))
                .or(qAnimation.name.containsIgnoreCase(keyword))
                .and(qEvent.status.ne(EventStatus.ENDED));

        return queryFactory.selectFrom(qEvent)
                .leftJoin(qEventAnimation).on(qEventAnimation.event.eq(qEvent))
                .leftJoin(qAnimation).on(qEventAnimation.animation.eq(qAnimation))
                .where(condition)
                .fetch();
    }

    @Override
    public List<Place> searchPlacesByKeyword(String keyword) {

        QPlace qPlace = QPlace.place;
        QPlaceAnimation qPlaceAnimation = QPlaceAnimation.placeAnimation;
        QAnimation qAnimation = QAnimation.animation;

        BooleanBuilder condition = new BooleanBuilder();
        condition.or(qPlace.name.containsIgnoreCase(keyword))
                .or(qAnimation.name.containsIgnoreCase(keyword));

        return queryFactory.selectFrom(qPlace)
                .leftJoin(qPlaceAnimation).on(qPlaceAnimation.place.eq(qPlace))
                .leftJoin(qAnimation).on(qPlaceAnimation.animation.eq(qAnimation))
                .where(condition)
                .fetch();
    }
}
