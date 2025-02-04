package com.otakumap.domain.route_like.service;

import com.otakumap.domain.route_like.dto.RouteLikeRequestDTO;
import com.otakumap.domain.route_like.entity.RouteLike;
import com.otakumap.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteLikeCommandService {
    void saveRouteLike(User user, Long routeId);
    void deleteRouteLike(List<Long> routeIds);
    void updateName(Long routeId, String name);
    RouteLike favoriteRouteLike(Long routeLikeId, RouteLikeRequestDTO.FavoriteDTO request);
    void saveCustomRouteLike(RouteLikeRequestDTO.SaveCustomRouteLikeDTO request, User user);
}
