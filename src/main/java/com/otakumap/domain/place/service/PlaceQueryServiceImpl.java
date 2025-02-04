package com.otakumap.domain.place.service;

import com.otakumap.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceQueryServiceImpl implements PlaceQueryService {
    private final PlaceRepository placeRepository;

    @Override
    public boolean isPlaceExist(Long placeId) {
        return placeRepository.existsById(placeId);
    }
}
