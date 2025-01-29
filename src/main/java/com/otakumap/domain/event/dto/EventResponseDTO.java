package com.otakumap.domain.event.dto;

import com.otakumap.domain.eventLocation.dto.EventLocationResponseDTO;
import com.otakumap.domain.image.dto.ImageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class EventResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventDTO {
        Long id;
        String title;
        LocalDate startDate;
        LocalDate endDate;
        ImageResponseDTO.ImageDTO thumbnail;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventDetailDTO {
        Long id;
        String title;
        String animationName;
        String name;
        String site;
        LocalDate startDate;
        LocalDate endDate;
        ImageResponseDTO.ImageDTO thumbnailImage;
        ImageResponseDTO.ImageDTO backgroundImage;
        ImageResponseDTO.ImageDTO goodsImage;
        EventLocationResponseDTO.EventLocationDTO eventLocation;
    }
}
