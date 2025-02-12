package com.otakumap.domain.place_review.service;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.domain.place_review.converter.PlaceReviewConverter;
import com.otakumap.domain.place_review.dto.PlaceReviewResponseDTO;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.repository.PlaceReviewRepository;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import com.otakumap.domain.place_short_review.repository.PlaceShortReviewRepository;
import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route_item.entity.RouteItem;
import com.otakumap.domain.route_item.repository.RouteItemRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceReviewQueryServiceImpl implements PlaceReviewQueryService {

    private final PlaceRepository placeRepository;
    private final PlaceReviewRepository placeReviewRepository;
    private final PlaceShortReviewRepository placeShortReviewRepository;
    private final RouteItemRepository routeItemRepository;

    @Override
    public PlaceReviewResponseDTO.PlaceAnimationReviewDTO getReviewsByPlace(Long placeId, int page, int size, String sort) {

        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

        // 전체 한 줄 리뷰 평균 별점 구하기
        List<PlaceShortReview> placeShortReviewList = placeShortReviewRepository.findAllByPlace(place);
        double avgRating = placeShortReviewList.stream()
                .mapToDouble(PlaceShortReview::getRating)
                .average()
                .orElse(0.0);
        Float finalAvgRating = (float)(Math.round(avgRating * 10) / 10.0);

        // 전체 리뷰 리스트
        List<RouteItem> routeItems = routeItemRepository.findAllByPlace(place);
        List<Route> routes = routeItems.stream()
                .map(RouteItem::getRoute)
                .toList();
        List<PlaceReview> allReviews = routes.stream()
                .map(placeReviewRepository::findAllByRoute).toList();

        // 애니메이션별로 그룹화
        Map<Animation, List<PlaceReview>> reviewsByAnimation = allReviews.stream()
                .collect(Collectors.groupingBy(review -> review.getPlaceAnimation().getAnimation()));

        // 애니메이션 그룹마다 그 안에 속한 리뷰들 페이징 적용
        List<PlaceReviewResponseDTO.AnimationReviewGroupDTO> animationGroups = paginateReviews(reviewsByAnimation, page, size);

        // 총 리뷰 수 계산
        long totalReviews = reviewsByAnimation.values().stream()
                .mapToLong(List::size)
                .sum();

        return PlaceReviewConverter.toPlaceAnimationReviewDTO(place, totalReviews, animationGroups, finalAvgRating);
    }

    private List<PlaceReviewResponseDTO.AnimationReviewGroupDTO> paginateReviews(Map<Animation, List<PlaceReview>> reviewsByAnimation, int page, int size) {

        return  reviewsByAnimation.entrySet().stream()
                .map(entry -> {
                    List<PlaceReview> reviews = entry.getValue();
                    int fromIndex = Math.min(page * size, reviews.size());
                    int toIndex = Math.min(fromIndex + size, reviews.size());

                    List<PlaceReview> pagedReviews = reviews.subList(fromIndex, toIndex);

                    return PlaceReviewConverter.toAnimationReviewGroupDTO(entry.getKey(), pagedReviews);
                })
                .filter(group -> !group.getReviews().isEmpty()) // 빈 그룹 제외
                .toList();
    }
}
