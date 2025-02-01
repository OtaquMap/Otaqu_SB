package com.otakumap.domain.place_like.service;

import com.otakumap.domain.place_like.dto.PlaceLikeResponseDTO;
import com.otakumap.domain.user.entity.User;

public interface PlaceLikeQueryService {
    PlaceLikeResponseDTO.PlaceLikePreViewListDTO getPlaceLikeList(User user, Long lastId, int limit);
    boolean isPlaceLikeExist(Long id);
}