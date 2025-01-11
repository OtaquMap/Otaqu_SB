package com.otakumap.domain.event_like.converter;

import com.otakumap.domain.event_like.dto.EventLikeResponseDTO;
import com.otakumap.domain.event_like.entity.EventLike;

import java.util.List;

public class EventLikeConverter {
    public static EventLikeResponseDTO.EventLikePreViewDTO eventLikePreViewDTO(EventLike eventLike) {
        return EventLikeResponseDTO.EventLikePreViewDTO.builder()
                .id(eventLike.getId())
                .eventId(eventLike.getEvent().getId())
                .name(eventLike.getEvent().getName())
                .thumbnail(eventLike.getEvent().getThumbnail())
                .startDate(eventLike.getEvent().getStartDate())
                .endDate(eventLike.getEvent().getEndDate())
                .isFavorite(eventLike.getIsFavorite())
                .eventType(eventLike.getEvent().getType())
                .build();

    }
    public static EventLikeResponseDTO.EventLikePreViewListDTO eventLikePreViewListDTO(List<EventLikeResponseDTO.EventLikePreViewDTO> eventLikes, boolean hasNext, Long lastId) {
        return EventLikeResponseDTO.EventLikePreViewListDTO.builder()
                .eventLikes(eventLikes)
                .hasNext(hasNext)
                .lastId(lastId)
                .build();
    }
}
