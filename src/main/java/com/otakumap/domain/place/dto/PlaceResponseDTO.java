package com.otakumap.domain.place.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
public class PlaceResponseDTO {

    @JsonProperty("results")
    private List<SearchedPlaceDTO> results;

    @Data
    public static class SearchedPlaceDTO {

        @JsonProperty("formatted_address")
        private String formattedAddress;

        @JsonProperty("geometry")
        private Geometry geometry;

        @JsonProperty("name")
        private String name;

        @Data
        public static class Geometry {
            @JsonProperty("location")
            private Location location;

            @Data
            public static class Location {
                private double lat;
                private double lng;
            }
        }

    }
}
