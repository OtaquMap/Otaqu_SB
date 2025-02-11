package com.otakumap.domain.event.converter;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event_location.converter.EventLocationConverter;
import com.otakumap.domain.hash_tag.dto.HashTagResponseDTO;
import com.otakumap.domain.image.converter.ImageConverter;

import java.util.List;

public class EventConverter {

    public static EventResponseDTO.EventDTO toEventDTO(Event event) {
        return EventResponseDTO.EventDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .thumbnail(ImageConverter.toImageDTO(event.getThumbnailImage()))
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }

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

    public static EventResponseDTO.SearchedEventInfoDTO toSearchedEventInfoDTO(Event event, Boolean isFavorite, String animationTitle, List<HashTagResponseDTO.HashTagDTO> hashTags) {

        return EventResponseDTO.SearchedEventInfoDTO.builder()
                .eventId(event.getId())
                .name(event.getName())
                .isLiked(isFavorite)
                .animationTitle(animationTitle)
                .hashTags(hashTags)
                .build();
    }
}
