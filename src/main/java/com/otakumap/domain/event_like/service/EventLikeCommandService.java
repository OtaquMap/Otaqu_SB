package com.otakumap.domain.event_like.service;

import java.util.List;

public interface EventLikeCommandService {
    void deleteEventLike(List<Long> eventIds);
}
