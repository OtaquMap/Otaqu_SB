package com.otakumap.domain.route.dto;

import com.otakumap.domain.route_item.dto.RouteItemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class RouteResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RouteDTO {
        Long routeId;
        List<RouteItemResponseDTO.RouteItemDTO> routeItems;
    }
}
