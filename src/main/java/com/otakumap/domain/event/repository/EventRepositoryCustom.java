package com.otakumap.domain.event.repository;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.image.dto.ImageResponseDTO;

import java.util.List;

public interface EventRepositoryCustom {
    List<EventResponseDTO.EventDTO> getPopularEvents();
    ImageResponseDTO.ImageDTO getEventBanner();
}
