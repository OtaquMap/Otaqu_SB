package com.otakumap.domain.search.repository;

import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.place.entity.Place;

import java.util.List;

public interface SearchRepositoryCustom {
    List<Event> searchEventsByKeyword(String keyword);
    List<Place> searchPlacesByKeyword(String keyword);
}
