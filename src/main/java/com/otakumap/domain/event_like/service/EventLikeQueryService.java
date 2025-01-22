package com.otakumap.domain.event_like.service;

import com.otakumap.domain.event_like.dto.EventLikeResponseDTO;

public interface EventLikeQueryService {
    EventLikeResponseDTO.EventLikePreViewListDTO getEventLikeList(Long userId, Integer type, Long lastId, int limit);
    boolean isEventLikeExist(Long id);
}
