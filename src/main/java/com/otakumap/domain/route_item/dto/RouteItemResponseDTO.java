package com.otakumap.domain.route_item.dto;

import com.otakumap.domain.route_item.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RouteItemResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RouteItemDTO {
        Long routeItemId;
        Long itemId;
        ItemType itemType;
        Integer itemOrder;
    }
}
