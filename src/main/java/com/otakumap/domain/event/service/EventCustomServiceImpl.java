package com.otakumap.domain.event.service;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.event.repository.EventRepositoryCustom;
import com.otakumap.domain.image.dto.ImageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventCustomServiceImpl implements EventCustomService {
    private final EventRepositoryCustom eventRepository;

    @Override
    public List<EventResponseDTO.EventDTO> getPopularEvents() {
        return eventRepository.getPopularEvents();
    }

    @Override
    public ImageResponseDTO.ImageDTO getEventBanner() {
        return eventRepository.getEventBanner();
    }
}
