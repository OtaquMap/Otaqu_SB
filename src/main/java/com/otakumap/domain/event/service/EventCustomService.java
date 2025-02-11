package com.otakumap.domain.event.service;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.image.dto.ImageResponseDTO;

import java.util.List;

public interface EventCustomService {
    List<EventResponseDTO.EventDTO> getPopularEvents();
    ImageResponseDTO.ImageDTO getEventBanner();
}
