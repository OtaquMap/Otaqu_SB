package com.otakumap.domain.event.repository;

import com.otakumap.domain.event.converter.EventConverter;
import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event.entity.QEvent;
import com.otakumap.domain.image.converter.ImageConverter;
import com.otakumap.domain.image.dto.ImageResponseDTO;
import com.otakumap.domain.image.entity.Image;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.EventHandler;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<EventResponseDTO.EventDTO> getPopularEvents() {
        QEvent event = QEvent.event;

        List<Event> events = queryFactory.selectFrom(event)
                .where(event.endDate.goe(LocalDate.now())
                        .and(event.startDate.loe(LocalDate.now())))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(8)
                .fetch();

        return events.stream()
                .map(EventConverter::toEventDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImageResponseDTO.ImageDTO getEventBanner() {
        QEvent event = QEvent.event;

        Event targetEvent = queryFactory.selectFrom(event)
                .where(event.endDate.goe(LocalDate.now())
                        .and(event.startDate.loe(LocalDate.now()))
                        .and(event.thumbnailImage.isNotNull()))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .fetchFirst();

        if (targetEvent == null) {
            return ImageResponseDTO.ImageDTO.builder()
                    .fileUrl("default_banner_url")
                    .build();
        }

        return ImageConverter.toImageDTO((targetEvent)
                .getThumbnailImage());
    }
}
