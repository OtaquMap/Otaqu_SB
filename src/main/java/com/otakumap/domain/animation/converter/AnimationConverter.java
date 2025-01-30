package com.otakumap.domain.animation.converter;

import com.otakumap.domain.animation.DTO.AnimationResponseDTO;
import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.reviews.converter.ReviewConverter;

import java.util.List;

public class AnimationConverter {
    public static AnimationResponseDTO.AnimationResultDTO animationResultDTO(Animation animation) {
        return AnimationResponseDTO.AnimationResultDTO.builder()
                .animationId(animation.getId())
                .name(animation.getName())
                .build();
    }

    public static AnimationResponseDTO.AnimationResultListDTO animationResultListDTO(List<Animation> animations) {
        List<AnimationResponseDTO.AnimationResultDTO> animationResultDTOs = animations.stream()
                .map(AnimationConverter::animationResultDTO)
                .toList();

        return AnimationResponseDTO.AnimationResultListDTO.builder()
                .animations(animationResultDTOs)
                .listSize(animationResultDTOs.size())
                .build();
    }
}
