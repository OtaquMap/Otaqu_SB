package com.otakumap.domain.place_like.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class PlaceLikeResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceLikePreViewDTO {
        Long id;
        Long userId;
        Long placeId;
        Boolean isFavorite;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceLikePreViewListDTO {
        List<PlaceLikeResponseDTO.PlaceLikePreViewDTO> placeLikes;
        boolean hasNext;
        Long lastId;
    }
}