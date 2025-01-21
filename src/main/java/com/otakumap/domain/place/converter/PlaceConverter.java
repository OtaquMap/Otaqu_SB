package com.otakumap.domain.place.converter;

import com.otakumap.domain.place.dto.PlaceResponseDTO;
import com.otakumap.domain.place.entity.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceConverter {
    public static PlaceResponseDTO convert(Place place) {
        return new PlaceResponseDTO(
                place.getId(),
                place.getName(),
                place.getLat(),
                place.getLng(),
                place.getDescription(),
                place.getSavedAt(),
                place.getIsFavorite()
        );
    }
}
