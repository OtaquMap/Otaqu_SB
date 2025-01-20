package com.otakumap.domain.place_like.service;

import com.otakumap.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceLikeCommandService {
    void deletePlaceLike(List<Long> placeIds);

    void savePlaceLike(User user, Long placeId);
}
