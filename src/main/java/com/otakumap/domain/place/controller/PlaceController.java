package com.otakumap.domain.place.controller;

import com.otakumap.domain.place.dto.PlaceResponseDTO;
import com.otakumap.domain.place.service.GooglePlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlaceController {

    private final GooglePlacesService googlePlacesService;


    @GetMapping("/places/search")
    public PlaceResponseDTO searchPlaces(@RequestParam String query) {
        return googlePlacesService.searchPlaces(query);
    }

}
