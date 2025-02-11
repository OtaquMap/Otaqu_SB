package com.otakumap.domain.animation.converter;

import com.otakumap.domain.animation.dto.AnimationResponseDTO;
import com.otakumap.domain.hash_tag.dto.HashTagResponseDTO;
import com.otakumap.domain.mapping.PlaceAnimation;

import java.util.List;

public class AnimationConverter {

    public static AnimationResponseDTO.AnimationInfoDTO toAnimationInfoDTO(PlaceAnimation placeAnimation, Boolean isFavorite,
                                                                           List<HashTagResponseDTO.HashTagDTO> hashTags) {
        return AnimationResponseDTO.AnimationInfoDTO.builder()
                .animationId(placeAnimation.getAnimation().getId())
                .animationName(placeAnimation.getAnimation().getName())
                .isFavorite(isFavorite)
                .hashTags(hashTags)
                .build();
    }
}
