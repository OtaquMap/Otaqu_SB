package com.otakumap.domain.place_like.service;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.domain.place_like.converter.PlaceLikeConverter;
import com.otakumap.domain.place_like.entity.PlaceLike;
import com.otakumap.domain.place_like.repository.PlaceLikeRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceLikeCommandServiceImpl implements PlaceLikeCommandService {
    private final PlaceLikeRepository placeLikeRepository;
    private final EntityManager entityManager;
    private final PlaceRepository placeRepository;

    @Override
    public void deletePlaceLike(List<Long> placeIds) {
        placeLikeRepository.deleteAllByIdInBatch(placeIds);
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void savePlaceLike(User user, Long placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

        if (placeLikeRepository.existsByUserAndPlace(user, place)) {
            throw new PlaceHandler(ErrorStatus.PLACE_LIKE_ALREADY_EXISTS);
        }

        PlaceLike placeLike = PlaceLikeConverter.toPlaceLike(user, place);
        placeLikeRepository.save(placeLike);
    }
}