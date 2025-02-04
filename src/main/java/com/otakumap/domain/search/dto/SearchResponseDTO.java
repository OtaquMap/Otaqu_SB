package com.otakumap.domain.search.dto;

import com.otakumap.domain.event.dto.EventResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class SearchResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchResultDTO {
        private Double latitude;
        private Double longitude;
        private int eventCount;
        private List<EventResponseDTO.SearchedEventInfoDTO> events;
    }
}
