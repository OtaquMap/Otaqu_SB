package com.otakumap.domain.eventLocation.converter;

import com.otakumap.domain.eventLocation.dto.EventLocationResponseDTO;
import com.otakumap.domain.eventLocation.entity.EventLocation;

public class EventLocationConverter {

    public static EventLocationResponseDTO.EventLocationDTO toEventLocationDTO(EventLocation eventLocation) {

        return EventLocationResponseDTO.EventLocationDTO.builder()
                .id(eventLocation.getId())
                .name(eventLocation.getName())
                .latitude(eventLocation.getLatitude())
                .longitude(eventLocation.getLongitude())
                .build();
    }
}
