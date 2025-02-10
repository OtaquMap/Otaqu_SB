package com.otakumap.domain.route.converter;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.reviews.dto.ReviewRequestDTO;
import com.otakumap.domain.route.dto.RouteResponseDTO;
import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route_item.converter.RouteItemConverter;
import com.otakumap.domain.route_item.entity.RouteItem;
import com.otakumap.domain.route_item.enums.ItemType;

import java.util.List;
import com.otakumap.domain.route_item.entity.RouteItem;

import java.util.List;

public class RouteConverter {

    public static RouteResponseDTO.RouteDTO toRouteDTO(Route route) {

        return RouteResponseDTO.RouteDTO.builder()
                .routeId(route.getId())
                .routeItems(route.getRouteItems().stream()
                        .map(RouteItemConverter::toRouteItemDTO).toList())
                .build();
    }

    public static Route toRoute(String name, List<RouteItem> routeItems) {
        Route route = Route.builder()
                .name(name)
                .routeItems(routeItems)
                .build();

        for (RouteItem item : routeItems) {
            item.setRoute(route);
        }

        return route;
    }
}
