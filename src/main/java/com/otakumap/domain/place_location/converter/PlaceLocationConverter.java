package com.otakumap.domain.place_location.converter;

import com.otakumap.domain.eventLocation.entity.EventLocation;
import com.otakumap.domain.place_location.dto.PlaceLocationResponse;

public class PlaceLocationConverter {

    public static PlaceLocationResponse toEventLocationDTO(EventLocation eventLocation) {
        return new PlaceLocationResponse(
                eventLocation.getId(),
                eventLocation.getName(),
                eventLocation.getLatitude(),
                eventLocation.getLongitude()
        );
    }
}