package com.otakumap.domain.place.service;

import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place_animation.repository.PlaceAnimationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceQueryServiceImpl implements PlaceQueryService {
    private final PlaceAnimationRepository placeAnimationRepository;

    @Override
    @Transactional
    public List<PlaceAnimation> getPlaceAnimations(Long placeId) {
        return placeAnimationRepository.findByPlaceId(placeId);
    }
}
