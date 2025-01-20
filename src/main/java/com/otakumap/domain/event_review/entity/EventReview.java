package com.otakumap.domain.event_review.entity;

import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.image.entity.Image;
import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EventReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(columnDefinition = "text not null")
    private String content;

    @Column(columnDefinition = "bigint default 0 not null")
    private Long view;

    @Column(nullable = false)
    private Float rating;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;

}
