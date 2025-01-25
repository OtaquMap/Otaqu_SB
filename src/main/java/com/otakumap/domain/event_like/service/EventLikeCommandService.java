package com.otakumap.domain.event_like.service;

import com.otakumap.domain.user.entity.User;

import java.util.List;

public interface EventLikeCommandService {
    void addEventLike(User user, Long eventId);
    void deleteEventLike(List<Long> eventIds);
}
