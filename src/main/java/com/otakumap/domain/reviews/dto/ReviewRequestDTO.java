package com.otakumap.domain.reviews.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {
    @Getter
    public static class CreateDTO {
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "내용을 입력해주세요.")
        private String content;

        private Long eventId;
        private Long placeId;

        @NotNull(message = "애니메이션 id를 입력해주세요.")
        private Long animeId;

        @Size(min = 1, message = "루트 아이템은 최소 1개 이상 필요합니다.")
        private List<RouteDTO> routeItems;
    }

    @Getter
    public static class RouteDTO {
        @NotNull(message = "itemId를 입력해주세요.")
        private Long itemId;

        @NotNull(message = "itemType을 입력해주세요.")
        private ItemType itemType;

        @NotNull(message = "order를 입력해주세요.")
        private Integer order;
    }

    public enum ItemType {
        PLACE, EVENT
    }
}
