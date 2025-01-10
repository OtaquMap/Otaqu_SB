package com.otakumap.domain.eventLocation.entity;

import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event.entity.enums.EventStatus;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EventLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String latitude;

    @Column(columnDefinition = "TEXT")
    private String longitude;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;
}
