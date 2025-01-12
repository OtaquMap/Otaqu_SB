package com.otakumap.domain.place_like.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceLikeCommandService {
    void deletePlaceLike(List<Long> placeIds);
}
