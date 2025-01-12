package com.otakumap.domain.event.service;

import com.otakumap.domain.event.dto.EventResponseDTO;

public interface EventQueryService {
    EventResponseDTO.EventDetailDTO getEventDetail(Long eventId);
}
