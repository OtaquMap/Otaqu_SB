package com.otakumap.domain.place.service;

import com.otakumap.domain.mapping.PlaceAnimation;

import java.util.List;

public interface PlaceQueryService {
    List<PlaceAnimation> getPlaceAnimations(Long placeId);
    boolean isPlaceExist(Long placeId);
}
