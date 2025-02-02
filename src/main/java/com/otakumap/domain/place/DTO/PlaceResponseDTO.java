package com.otakumap.domain.place.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PlaceResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceAnimationListDTO {
        List<PlaceAnimationDTO> placeAnimations;
        int listSize;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceAnimationDTO {
        private Long placeAnimationId;
        private Long animationId;
        private String animationName;
    }
}
