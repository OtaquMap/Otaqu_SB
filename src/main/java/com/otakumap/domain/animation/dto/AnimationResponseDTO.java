package com.otakumap.domain.animation.dto;

import com.otakumap.domain.hash_tag.dto.HashTagResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class AnimationResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimationInfoDTO {
        private Long animationId;
        private String animationName;
        private Boolean isLiked;
        private List<HashTagResponseDTO.HashTagDTO> hashTags;
    }
}
