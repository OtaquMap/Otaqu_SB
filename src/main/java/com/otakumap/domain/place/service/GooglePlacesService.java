package com.otakumap.domain.place.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otakumap.domain.place.dto.PlaceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GooglePlacesService {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PlaceResponseDTO searchPlaces(String query) {
        String url = String.format(
                "https://maps.googleapis.com/maps/api/place/textsearch/json?query=%s&key=%s",
                query,
                apiKey
        );

        // API 호출
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response); // 실제 API 응답 확인

        try {
            // 응답 데이터를 DTO로 변환
            return objectMapper.readValue(response, PlaceResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Google Places API response", e);
        }
    }
}
