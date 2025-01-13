package com.otakumap.domain.place_like.converter;

import com.otakumap.domain.place_like.dto.PlaceLikeResponseDTO;
import com.otakumap.domain.place_like.entity.PlaceLike;

import java.util.List;

public class PlaceLikeConverter {
    public static PlaceLikeResponseDTO.PlaceLikePreViewDTO placeLikePreViewDTO(PlaceLike placeLike) {
        return PlaceLikeResponseDTO.PlaceLikePreViewDTO.builder()
                .id(placeLike.getId())
                .userId(placeLike.getUser().getId())
                .placeId(placeLike.getPlace().getId())
                .isFavorite(placeLike.getIsFavorite())
                .build();

    }

    public static PlaceLikeResponseDTO.PlaceLikePreViewListDTO placeLikePreViewListDTO(List<PlaceLikeResponseDTO.PlaceLikePreViewDTO> placeLikes, boolean hasNext, Long lastId) {
        return PlaceLikeResponseDTO.PlaceLikePreViewListDTO.builder()
                .placeLikes(placeLikes)
                .hasNext(hasNext)
                .lastId(lastId)
                .build();
    }
}