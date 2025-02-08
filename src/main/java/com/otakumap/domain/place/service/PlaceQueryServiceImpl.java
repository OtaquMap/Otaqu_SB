package com.otakumap.domain.place.service;

import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place_animation.repository.PlaceAnimationRepository;
import jakarta.transaction.Transactional;
import com.otakumap.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceQueryServiceImpl implements PlaceQueryService {
    private final PlaceRepository placeRepository;
    private final PlaceAnimationRepository placeAnimationRepository;

    @Override
    public boolean isPlaceExist(Long placeId) {
        return placeRepository.existsById(placeId);
    }

    @Override
    @Transactional
    public List<PlaceAnimation> getPlaceAnimations(Long placeId) {
        return placeAnimationRepository.findByPlaceId(placeId);
    }
}
