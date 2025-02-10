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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceDetailDTO {
        private Long id;
        private String name;
        private Double latitude;
        private Double longitude;
        private Boolean isFavorite;
        private Boolean isLiked;
        private PlaceResponseDTO.PlaceAnimationListDTO animationListDTO;
        private List<String> hashtags;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceDTO {
        private Long id;
        private String name;
        private Double latitude;
        private Double longitude;
    }
}
