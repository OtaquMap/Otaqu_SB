package com.otakumap.domain.route_like.dto;

import com.otakumap.global.validation.annotation.ExistPlace;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class RouteLikeRequestDTO {
    @Getter
    public static class FavoriteDTO {
        @NotNull(message = "즐겨찾기 여부 입력은 필수입니다.")
        Boolean isFavorite;
    }

    @Getter
    public static class SaveCustomRouteLikeDTO {
        @NotBlank(message = "루트 이름 입력은 필수입니다.")
        String name;
        @NotEmpty(message = "루트 아이템 입력은 필수입니다.")
        List<RouteItemDTO> routeItems;
    }

    @Getter
    public static class RouteItemDTO {
        @NotBlank(message = "루트 아이템에 해당하는 장소 이름 입력은 필수입니다.")
        String name;
        @ExistPlace
        Long placeId;
        @NotNull(message = "루트 아이템 순서 입력은 필수입니다.")
        Integer itemOrder;
    }
}
