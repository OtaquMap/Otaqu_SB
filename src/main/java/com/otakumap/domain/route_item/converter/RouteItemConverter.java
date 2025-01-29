package com.otakumap.domain.route_item.converter;

import com.otakumap.domain.route_item.dto.RouteItemResponseDTO;
import com.otakumap.domain.route_item.entity.RouteItem;

public class RouteItemConverter {

    public static RouteItemResponseDTO.RouteItemDTO toRouteItemDTO(RouteItem routeItem) {
        return RouteItemResponseDTO.RouteItemDTO.builder()
                .routeItemId(routeItem.getId())
                .itemId(routeItem.getItemId())
                .itemType(routeItem.getItemType())
                .itemOrder(routeItem.getItemOrder())
                .build();
    }
}
