package com.otakumap.domain.search.service;

import com.otakumap.domain.event.converter.EventConverter;
import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event.entity.enums.EventStatus;
import com.otakumap.domain.event_like.entity.EventLike;
import com.otakumap.domain.event_like.repository.EventLikeRepository;
import com.otakumap.domain.event_location.entity.EventLocation;
import com.otakumap.domain.event_location.repository.EventLocationRepository;
import com.otakumap.domain.hash_tag.entity.HashTag;
import com.otakumap.domain.mapping.EventHashTag;
import com.otakumap.domain.mapping.repository.EventHashTagRepository;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.search.converter.SearchConverter;
import com.otakumap.domain.search.dto.SearchResponseDTO;
import com.otakumap.domain.search.repository.SearchRepositoryCustom;
import com.otakumap.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchRepositoryCustom searchRepository;
    private final EventLocationRepository eventLocationRepository;
    private final EventLikeRepository eventLikeRepository;
    private final EventHashTagRepository eventHashTagRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SearchResponseDTO.SearchResultDTO> getSearchedResult (User user, String keyword) {

        List<Event> events = searchRepository.searchEventsByKeyword(keyword);
        List<Place> places = searchRepository.searchPlacesByKeyword(keyword);

        List<Place> safePlaces = places != null ? places : Collections.emptyList(); // 불변
        List<Event> safeEvents = events != null ? events : new ArrayList<>(); // 가변

        // 검색한 결과에 장소가 있을 때 -> 각 장소의 위/경도와 같은 곳에서 진행되고 있는 event를 찾음
        List<Event> newEvents = safePlaces.stream()
                .flatMap(place -> eventLocationRepository.findByLatAndLng(place.getLat(), place.getLng()).stream())
                .map(EventLocation::getEvent)
                .filter(event -> event != null && event.getStatus() != EventStatus.ENDED)
                .toList();

        // 기존에 검색된 이벤트와 합치기
        List<Event> combinedEvents =
                Stream.concat(safeEvents.stream(), newEvents.stream())
                        .distinct() // 중복 이벤트 제거
                        .toList();

        // 위도,경도 조합으로 같은 것들끼리 그룹화
        Map<String, List<Event>> groupedEvents = combinedEvents.stream()
                .filter(event -> event.getEventLocation() != null)
                .collect(Collectors.groupingBy(event -> {
                    Double lat = event.getEventLocation().getLat();
                    Double lng = event.getEventLocation().getLng();
                    return lat + "," + lng;
                }));

        return groupedEvents.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();  // "lat,lng" 형태의 키 값에서 위/경도 추출
                    String[] parts = key.split(",");
                    Double lat = Double.valueOf(parts[0]);
                    Double lng = Double.valueOf(parts[1]);

                    // 각 event를 DTO로 변환
                    List<EventResponseDTO.SearchedEventInfoDTO> eventDTOs = entry.getValue().stream()
                            .map(event -> {
                                EventLike eventLike = eventLikeRepository.findByUserAndEvent(user, event);
                                boolean isFavorite = eventLike != null && eventLike.getIsFavorite() == Boolean.TRUE;

                                List<EventHashTag> eventHashTags = eventHashTagRepository.findByEvent(event);
                                List<HashTag> hashTags = eventHashTags.stream()
                                        .map(EventHashTag::getHashTag)
                                        .collect(Collectors.toList());

                                return EventConverter.toSearchedEventInfoDTO(event, isFavorite, hashTags);
                            })
                            .collect(Collectors.toList());

                    return SearchConverter.toSearchResultDTO(eventDTOs, lat, lng);
                })
                .toList();
    }

}
