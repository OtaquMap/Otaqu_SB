package com.otakumap.domain.place_review.service;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.domain.place_review.converter.PlaceReviewConverter;
import com.otakumap.domain.place_review.dto.PlaceReviewResponseDTO;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.repository.PlaceReviewRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceReviewQueryServiceImpl implements PlaceReviewQueryService {

    private final PlaceRepository placeRepository;
    private final PlaceReviewRepository placeReviewRepository;

    @Override
    public PlaceReviewResponseDTO.PlaceAnimationReviewDTO getReviewsByPlace(Long placeId, int page, int size, String sort) {

        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

        Map<Animation, List<PlaceReview>> reviewsByAnimation = placeReviewRepository.findReviewsByPlaceWithAnimation(placeId, page, size, sort);

        return PlaceReviewConverter.toPlaceAnimationReviewDTO(place, reviewsByAnimation);
    }
}
