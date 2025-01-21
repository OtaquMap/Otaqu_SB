package com.otakumap.domain.place.service;

import com.otakumap.domain.place.dto.PlaceResponseDTO;
import com.otakumap.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<PlaceResponseDTO> getSavedPlaces(Long userId) {
        return placeRepository.findByUserId(userId).stream()
                .map(place -> new PlaceResponseDTO(
                        place.getId(),
                        place.getName(),
                        place.getLat(),
                        place.getLng(),
                        place.getDescription(),
                        place.getSavedAt(),
                        place.getIsFavorite()
                ))
                .collect(Collectors.toList());
    }

}

