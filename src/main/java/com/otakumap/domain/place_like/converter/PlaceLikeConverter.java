package com.otakumap.domain.place_like.converter;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_like.dto.PlaceLikeResponseDTO;
import com.otakumap.domain.place_like.entity.PlaceLike;
import com.otakumap.domain.user.entity.User;

import java.util.List;

public class PlaceLikeConverter {
    public static PlaceLikeResponseDTO.PlaceLikePreViewDTO placeLikePreViewDTO(PlaceLike placeLike) {
        return PlaceLikeResponseDTO.PlaceLikePreViewDTO.builder()
                .id(placeLike.getId())
                .placeId(placeLike.getPlace().getId())
                .name(placeLike.getPlace().getName())
                .isFavorite(placeLike.getIsFavorite())
                .detail(placeLike.getPlace().getDetail())
                .build();

    }

    public static PlaceLikeResponseDTO.PlaceLikePreViewListDTO placeLikePreViewListDTO(List<PlaceLikeResponseDTO.PlaceLikePreViewDTO> placeLikes, boolean hasNext, Long lastId) {
        return PlaceLikeResponseDTO.PlaceLikePreViewListDTO.builder()
                .placeLikes(placeLikes)
                .hasNext(hasNext)
                .lastId(lastId)
                .build();
    }

    public static PlaceLike toPlaceLike(User user, Place place) {
        return PlaceLike.builder()
                .user(user)
                .place(place)
                .isFavorite(Boolean.TRUE)
                .build();
    }
}