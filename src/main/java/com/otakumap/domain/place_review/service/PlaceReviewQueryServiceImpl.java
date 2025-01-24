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
import java.util.stream.Collectors;

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

        // 전체 리뷰 리스트
        List<PlaceReview> allReviews = placeReviewRepository.findAllReviewsByPlace(placeId, sort);

        // 애니메이션별로 그룹화
        Map<Animation, List<PlaceReview>> reviewsByAnimation = allReviews.stream()
                .collect(Collectors.groupingBy(review -> review.getPlaceAnimation().getAnimation()));

        // 애니메이션 그룹마다 그 안에 속한 리뷰들 페이징 적용
        List<PlaceReviewResponseDTO.AnimationReviewGroupDTO> animationGroups = reviewsByAnimation.entrySet().stream()
                .map(entry -> {
                    List<PlaceReview> reviews = entry.getValue();
                    int fromIndex = Math.min(page * size, reviews.size());
                    int toIndex = Math.min(fromIndex + size, reviews.size());

                    List<PlaceReview> pagedReviews = reviews.subList(fromIndex, toIndex);

                    return PlaceReviewConverter.toAnimationReviewGroupDTO(entry.getKey(), pagedReviews);
                })
                .filter(group -> !group.getReviews().isEmpty()) // 빈 그룹 제외
                .toList();

        // 총 리뷰 수 계산
        long totalReviews = reviewsByAnimation.values().stream()
                .mapToLong(List::size)
                .sum();

        return PlaceReviewConverter.toPlaceAnimationReviewDTO(place, totalReviews, animationGroups);
    }
}
