package com.otakumap.domain.notification.scheduler;

import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event_like.entity.EventLike;
import com.otakumap.domain.event.repository.EventRepository;
import com.otakumap.domain.notification.entity.enums.NotificationType;
import com.otakumap.domain.notification.service.NotificationCommandService;
import com.otakumap.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventScheduler {
    private final EventRepository eventRepository;
    private final NotificationCommandService notificationCommandService;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void notifyEventStart() {
        LocalDate today = LocalDate.now();
        List<Event> eventsStartingToday = eventRepository.findAllByStartDate(today);

        for (Event event : eventsStartingToday) {
            // EventLike 리스트에서 User 추출 후 알림 전송
            for (User user : event.getEventLikeList().stream()
                    .map(EventLike::getUser)
                    .toList()) {
                // 알림 메시지에 이벤트 이름 포함
                String message = String.format(
                        "%s가 시작되었습니다! 지금 바로 확인하세요!",
                        event.getName() // 이벤트 이름 가져오기
                );

                notificationCommandService.send(
                        user,
                        NotificationType.EVENT_START,
                        message,
                        "/events/" + event.getId() // 이벤트 상세 페이지 링크
                );
            }
        }
    }
}
