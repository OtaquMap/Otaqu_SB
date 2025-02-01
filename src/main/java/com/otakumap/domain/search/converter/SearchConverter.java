package com.otakumap.domain.search.converter;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.search.dto.SearchResponseDTO;

import java.util.List;

public class SearchConverter {

    public static SearchResponseDTO.SearchResultDTO toSearchResultDTO(List<EventResponseDTO.SearchedEventInfoDTO> eventDTOs, Double lat, Double lng) {
        return SearchResponseDTO.SearchResultDTO.builder()
                .latitude(lat)
                .longitude(lng)
                .eventCount(eventDTOs.size())
                .events(eventDTOs)
                .build();
    }
}
