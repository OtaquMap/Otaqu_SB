package com.otakumap.domain.place.service;

import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place.DTO.PlaceResponseDTO;

import java.util.List;

public interface PlaceQueryService {
    List<PlaceAnimation> getPlaceAnimations(Long placeId);
    boolean isPlaceExist(Long placeId);
    PlaceResponseDTO.PlaceDetailDTO getPlaceDetail(Long routeId, Long placeId);
}
