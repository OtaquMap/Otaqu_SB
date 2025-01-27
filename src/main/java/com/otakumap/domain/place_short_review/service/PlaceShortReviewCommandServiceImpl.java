package com.otakumap.domain.place_short_review.service;

import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.domain.place_animation.repository.PlaceAnimationRepository;
import com.otakumap.domain.place_short_review.converter.PlaceShortReviewConverter;
import com.otakumap.domain.place_short_review.DTO.PlaceShortReviewRequestDTO;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import com.otakumap.domain.place_short_review.repository.PlaceShortReviewRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import com.otakumap.global.apiPayload.exception.handler.UserHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceShortReviewCommandServiceImpl implements PlaceShortReviewCommandService {
    private final PlaceShortReviewRepository placeShortReviewRepository;;
    private final PlaceRepository placeRepository;
    private final PlaceAnimationRepository placeAnimationRepository;

    @Override
    @Transactional
    public PlaceShortReview createReview(User user, Long placeId, PlaceShortReviewRequestDTO.CreateDTO request) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

        PlaceAnimation placeAnimation = placeAnimationRepository.findById(request.getPlaceAnimationId())
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_ANIMATION_NOT_FOUND));

        PlaceShortReview newReview = PlaceShortReviewConverter.toPlaceShortReview(request, user, place, placeAnimation);

        return placeShortReviewRepository.save(newReview);
    }
}