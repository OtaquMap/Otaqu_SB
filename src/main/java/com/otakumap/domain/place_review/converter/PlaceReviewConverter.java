package com.otakumap.domain.place_review.converter;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.hash_tag.converter.HashTagConverter;
import com.otakumap.domain.hash_tag.dto.HashTagResponseDTO;
import com.otakumap.domain.image.converter.ImageConverter;
import com.otakumap.domain.mapping.PlaceReviewPlace;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_review.dto.PlaceReviewRequestDTO;
import com.otakumap.domain.place_review.dto.PlaceReviewResponseDTO;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceReviewConverter {
    public static PlaceReviewResponseDTO.ReviewCreateResponseDTO toReviewCreateResponseDTO(PlaceReview placeReview) {
        return PlaceReviewResponseDTO.ReviewCreateResponseDTO.builder()
                .reviewId(placeReview.getId())
                .title(placeReview.getTitle())
                .content(placeReview.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

//    public static PlaceReview toPlaceReview(PlaceReviewRequestDTO.ReviewCreateRequestDTO request, User user, List<PlaceReviewPlace> placeReviewPlaces) {
//        return PlaceReview.builder()
//                .user(user)
//                .placeList(placeReviewPlaces)
//                .title(request.getTitle())
//                .content(request.getContent())
//                .view(0L)
//                .build();
//    }

    // PlaceReview -> PlaceReviewDTO 변환
    public static PlaceReviewResponseDTO.PlaceReviewDTO toPlaceReviewDTO(PlaceReview placeReview) {

        return PlaceReviewResponseDTO.PlaceReviewDTO.builder()
                .reviewId(placeReview.getId())
                .placeIds(placeReview.getPlaceList().stream().map(prp -> prp.getPlace().getId()).collect(Collectors.toList()))
                .title(placeReview.getTitle())
                .content(placeReview.getContent())
                .view(placeReview.getView())
                .createdAt(placeReview.getCreatedAt())
                .reviewImage(ImageConverter.toImageDTO(placeReview.getImages().get(0))) // 나중에 수정
                .type("place")
                .build();
    }

    // 해당 장소의 애니메이션별 리뷰 그룹 생성
    public static PlaceReviewResponseDTO.AnimationReviewGroupDTO toAnimationReviewGroupDTO(Animation animation, List<PlaceReview> reviews) {

        List<PlaceReviewResponseDTO.PlaceReviewDTO> reviewDTOs = reviews.stream()
                .map(PlaceReviewConverter::toPlaceReviewDTO)
                .toList();

        return PlaceReviewResponseDTO.AnimationReviewGroupDTO.builder()
                .animationId(animation.getId())
                .animationName(animation.getName())
                .reviews(reviewDTOs)
                .totalReviews(reviews.size())
                .build();
    }

    // 최상위 결과 DTO 생성
    public static PlaceReviewResponseDTO.PlaceAnimationReviewDTO toPlaceAnimationReviewDTO(Place place, long totalReviews,
                                                                                           List<PlaceReviewResponseDTO.AnimationReviewGroupDTO> animationGroups,
                                                                                           Float avgRating) {

        List<HashTagResponseDTO.HashTagDTO> hashTagDTOs = place.getPlaceHashTagList()
                .stream()
                .map(placeHashTag -> HashTagConverter.toHashTagDTO(placeHashTag.getHashTag()))
                .toList();

        return PlaceReviewResponseDTO.PlaceAnimationReviewDTO.builder()
                .placeId(place.getId())
                .placeName(place.getName())
                .animationGroups(animationGroups)
                .totalReviews(totalReviews)
                .hashTags(hashTagDTOs)
                .avgRating(avgRating)
                .build();
    }
}
