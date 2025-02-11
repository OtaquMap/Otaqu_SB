package com.otakumap.domain.place.converter;

import com.otakumap.domain.animation.dto.AnimationResponseDTO;
import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place.DTO.PlaceResponseDTO;
import com.otakumap.domain.place.entity.Place;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceConverter {
    public static PlaceResponseDTO.PlaceAnimationDTO toPlaceAnimationDTO(PlaceAnimation placeAnimation) {
        return PlaceResponseDTO.PlaceAnimationDTO.builder()
                .placeAnimationId(placeAnimation.getId())
                .animationId(placeAnimation.getAnimation().getId())
                .animationName(placeAnimation.getAnimation().getName())
                .build();
    }

    public static PlaceResponseDTO.PlaceAnimationListDTO toPlaceAnimationListDTO(List<PlaceAnimation> placeAnimations) {
        return PlaceResponseDTO.PlaceAnimationListDTO.builder()
                .placeAnimations(placeAnimations.stream()
                        .map(PlaceConverter::toPlaceAnimationDTO)
                        .collect(Collectors.toList()))
                .listSize(placeAnimations.size())
                .build();
    }

    public static PlaceResponseDTO.PlaceDetailDTO toPlaceDetailDTO(Place place, PlaceResponseDTO.PlaceAnimationListDTO animationListDTO, List<String> hashtags, boolean isLiked) {
        return PlaceResponseDTO.PlaceDetailDTO.builder()
                .id(place.getId())
                .name(place.getName())
                .latitude(place.getLat())
                .longitude(place.getLng())
                .isFavorite(place.getIsFavorite())
                .isLiked(isLiked)
                .animationListDTO(animationListDTO)
                .hashtags(hashtags)
                .build();
    }

    public static List<String> toPlaceHashtagsDTO(List<PlaceAnimation> placeAnimations) {
        return placeAnimations.stream()
                .flatMap(placeAnimation -> placeAnimation.getPlaceAnimationHashTags().stream())
                .map(placeAnimationHashTag -> placeAnimationHashTag.getHashTag().getName())
                .collect(Collectors.toList());
    }

    public static PlaceResponseDTO.PlaceDTO toPlaceDTO(Place place) {
        return new PlaceResponseDTO.PlaceDTO(
                place.getId(),
                place.getName(),
                place.getLat(),
                place.getLng()
        );
    }

    public static List<PlaceResponseDTO.PlaceDTO> toPlaceDTOList(List<Place> places) {
        return places.stream()
                .map(PlaceConverter::toPlaceDTO)
                .collect(Collectors.toList());
    }

    public static PlaceResponseDTO.SearchedPlaceInfoDTO toSearchedPlaceInfoDTO(Place place,
                                                                               List<AnimationResponseDTO.AnimationInfoDTO> animationDTOs) {
        return PlaceResponseDTO.SearchedPlaceInfoDTO.builder()
                .placeId(place.getId())
                .name(place.getName())
                .animations(animationDTOs)
                .build();

    }
}
