package com.otakumap.domain.route_item.converter;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.reviews.dto.ReviewRequestDTO;
import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route_item.dto.RouteItemResponseDTO;
import com.otakumap.domain.route_item.entity.RouteItem;
import com.otakumap.domain.route_item.enums.ItemType;

public class RouteItemConverter {

    public static RouteItemResponseDTO.RouteItemDTO toRouteItemDTO(RouteItem routeItem) {
        return RouteItemResponseDTO.RouteItemDTO.builder()
                .routeItemId(routeItem.getId())
                .name(routeItem.getName())
                .itemId(routeItem.getItemId())
                .itemType(routeItem.getItemType())
                .itemOrder(routeItem.getItemOrder())
                .build();
    }

    public static RouteItem toRouteItem(ReviewRequestDTO.RouteDTO routeDTO, Place place, Route route) {
        return RouteItem.builder()
                .name(routeDTO.getName())
                .itemOrder(routeDTO.getOrder())
                .itemId(place.getId())
                .itemType(ItemType.PLACE)
                .route(route)
                .place(place)
                .build();
    }
}
