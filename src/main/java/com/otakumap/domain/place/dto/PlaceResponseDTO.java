package com.otakumap.domain.place.dto;

import java.time.LocalDateTime;


public record PlaceResponseDTO(
        Long id,
        String name,
        Double lat,
        Double lng,
        String description,
        LocalDateTime savedAt
) {
    // 필요한 경우, 추가적인 메서드를 여기에 정의할 수 있습니다.
}
