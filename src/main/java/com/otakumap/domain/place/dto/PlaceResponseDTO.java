package com.otakumap.domain.place.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PlaceResponseDTO {
    private Long id;
    private String name;
    private Double lat;
    private Double lng;
    private String description;
    private LocalDateTime savedAt;
}
