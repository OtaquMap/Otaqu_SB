package com.otakumap.domain.event.repository;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.image.dto.ImageResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EventRepositoryCustom {
    List<EventResponseDTO.EventDTO> getPopularEvents();
    ImageResponseDTO.ImageDTO getEventBanner();
    Page<EventResponseDTO.EventDTO> getEventByGenre(String genre, Integer page, Integer size);
    Page<EventResponseDTO.EventDTO> getEventByStatusAndType(String status, String type, Integer page, Integer size);
}
