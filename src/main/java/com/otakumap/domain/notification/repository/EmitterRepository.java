package com.otakumap.domain.notification.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository {
    SseEmitter save(String emitterId, SseEmitter emitter);
    void saveEventCache(String emitterId, Object event);
    Map<String, SseEmitter> findAllEmitterStartWithByUserId(String userId);
    Map<String, Object> findAllEventCacheStartWithByUserId(String userId);
    void deleteById(String emitterId);
    void deleteAllEmitterStartWithByUserId(String userId);
}
