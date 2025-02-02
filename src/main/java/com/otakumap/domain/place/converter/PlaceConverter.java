package com.otakumap.domain.place.converter;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place.DTO.PlaceResponseDTO;
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
}
