package com.otakumap.domain.place_short_review.service;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import com.otakumap.domain.place_short_review.repository.PlaceShortReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceShortReviewQueryServiceImpl implements PlaceShortReviewQueryService {

    private final PlaceRepository placeRepository;
    private final PlaceShortReviewRepository placeShortReviewRepository;

    @Override
    public Page<PlaceShortReview> getPlaceShortReviews(Long placeId, Integer page) {
        Place place = placeRepository.findById(placeId).get();
        return placeShortReviewRepository.findAllByPlace(place, PageRequest.of(page, 6));
    }
}
