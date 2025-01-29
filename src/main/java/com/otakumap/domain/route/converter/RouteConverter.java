package com.otakumap.domain.route.converter;

import com.otakumap.domain.route.dto.RouteResponseDTO;
import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route_item.converter.RouteItemConverter;

public class RouteConverter {

    public static RouteResponseDTO.RouteDTO toRouteDTO(Route route) {

        if(route == null) {
            return new RouteResponseDTO.RouteDTO();
        }

        return RouteResponseDTO.RouteDTO.builder()
                .routeId(route.getId())
                .routeItems(route.getRouteItems().stream()
                        .map(RouteItemConverter::toRouteItemDTO).toList())
                .build();
    }
}
