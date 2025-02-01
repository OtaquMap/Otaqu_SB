package com.otakumap.domain.reviews.service;

import com.otakumap.domain.event_review.entity.EventReview;
import com.otakumap.domain.event_review.repository.EventReviewRepository;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.repository.PlaceReviewRepository;
import com.otakumap.domain.reviews.converter.ReviewConverter;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import com.otakumap.domain.reviews.enums.ReviewType;
import com.otakumap.domain.reviews.repository.ReviewRepositoryCustom;
import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route_item.entity.RouteItem;
import com.otakumap.domain.route_item.enums.ItemType;
import com.otakumap.domain.route_item.repository.RouteItemRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.EventHandler;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import com.otakumap.global.apiPayload.exception.handler.ReviewHandler;
import com.otakumap.global.apiPayload.exception.handler.RouteItemHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepositoryCustom reviewRepositoryCustom;
    private final EventReviewRepository eventReviewRepository;
    private final PlaceReviewRepository placeReviewRepository;
    private final RouteItemRepository routeItemRepository;

    @Override
    public Page<ReviewResponseDTO.SearchedReviewPreViewDTO> searchReviewsByKeyword(String keyword, int page, int size, String sort) {

        return reviewRepositoryCustom.getReviewsByKeyword(keyword, page, size, sort);
    }

    @Override
    public ReviewResponseDTO.ReviewDetailDTO getReviewDetail(Long reviewId, ReviewType type) {

        if(type == ReviewType.EVENT) {
            EventReview eventReview = eventReviewRepository.findById(reviewId)
                    .orElseThrow(() -> new EventHandler(ErrorStatus.EVENT_REVIEW_NOT_FOUND));

            Route route = getRoute(eventReview.getId(), ItemType.EVENT);

            return ReviewConverter.toEventReviewDetailDTO(eventReview, route);
        } else if (type == ReviewType.PLACE) {
            PlaceReview placeReview = placeReviewRepository.findById(reviewId)
                    .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_REVIEW_NOT_FOUND));

            Route route = getRoute(placeReview.getId(), ItemType.PLACE);

            return ReviewConverter.toPlaceReviewDetailDTO(placeReview, route);
        } else {
            throw new ReviewHandler(ErrorStatus.INVALID_REVIEW_TYPE);
        }
    }

    private Route getRoute(Long reviewId, ItemType itemType) {

        List<RouteItem> routeItems;

        if (itemType == ItemType.EVENT) {
            routeItems = routeItemRepository.findByItemIdAndItemType(reviewId, ItemType.EVENT);
        } else {
            routeItems = routeItemRepository.findByItemIdAndItemType(reviewId, ItemType.PLACE);
        }

        if(routeItems.isEmpty()) {
            throw new RouteItemHandler(ErrorStatus.ROUTE_ITEM_NOT_FOUND);
        }

        return routeItems.get(0).getRoute();
    }
}
