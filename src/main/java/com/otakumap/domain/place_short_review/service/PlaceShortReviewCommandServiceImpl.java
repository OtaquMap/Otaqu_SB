package com.otakumap.domain.place_short_review.service;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.domain.place_short_review.converter.PlaceShortReviewConverter;
import com.otakumap.domain.place_short_review.dto.PlaceShortReviewRequestDTO;
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
    private final PlaceShortReviewRepository placeShortReviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @Override
    @Transactional
    public PlaceShortReview createReview(Long placeId, PlaceShortReviewRequestDTO.CreateDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

        PlaceShortReview newReview = PlaceShortReviewConverter.toPlaceShortReview(request, user);

        return placeShortReviewRepository.save(newReview);
    }
}