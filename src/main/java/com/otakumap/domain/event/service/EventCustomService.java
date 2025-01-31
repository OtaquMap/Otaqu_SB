package com.otakumap.domain.event.service;

import com.otakumap.domain.event.dto.EventResponseDTO;

import java.util.List;

public interface EventCustomService {
    List<EventResponseDTO.EventDTO> getPopularEvents();
}
