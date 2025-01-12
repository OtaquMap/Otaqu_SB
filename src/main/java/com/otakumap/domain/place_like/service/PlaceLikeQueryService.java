package com.otakumap.domain.place_like.service;

import com.otakumap.domain.place_like.dto.PlaceLikeResponseDTO;

public interface PlaceLikeQueryService {
    PlaceLikeResponseDTO.PlaceLikePreViewListDTO getPlaceLikeList(Long userId, Long lastId, int limit);
    boolean isPlaceLikeExist(Long id);
}