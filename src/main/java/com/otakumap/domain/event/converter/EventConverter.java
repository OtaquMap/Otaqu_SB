package com.otakumap.domain.event.converter;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.eventLocation.converter.EventLocationConverter;
import com.otakumap.domain.image.converter.ImageConverter;

public class EventConverter {

    public static EventResponseDTO.EventDetailDTO toEventDetailDTO(Event event) {
        return EventResponseDTO.EventDetailDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .animationName(event.getAnimationName())
                .name(event.getName())
                .site(event.getSite())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .thumbnailImage(ImageConverter.toImageDTO(event.getThumbnailImage()))
                .backgroundImage(ImageConverter.toImageDTO(event.getBackgroudImage()))
                .goodsImage(ImageConverter.toImageDTO(event.getGoodsImage()))
                .eventLocation(EventLocationConverter.toEventLocationDTO(event.getEventLocation()))
                .build();
    }
}
