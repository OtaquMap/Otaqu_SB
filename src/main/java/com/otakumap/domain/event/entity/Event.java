package com.otakumap.domain.event.entity;

import com.otakumap.domain.event.entity.enums.EventStatus;
import com.otakumap.domain.event.entity.enums.EventType;
import com.otakumap.domain.event.entity.enums.Genre;
import com.otakumap.domain.event_like.entity.EventLike;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private EventType type;

    @Column(columnDefinition = "TEXT")
    private String thumbnail;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    private EventStatus status;

    @Column(columnDefinition = "TEXT")
    private String site;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventLike> eventLikeList = new ArrayList<>();
}
