package com.otakumap.domain.event.repository;

import com.otakumap.domain.event.dto.EventResponseDTO;
import java.util.List;

public interface EventRepositoryCustom {
    List<EventResponseDTO.EventDTO> getPopularEvents();
}
