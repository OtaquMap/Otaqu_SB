package com.otakumap.domain.notification.entity;

import com.otakumap.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("true")
    private Boolean community_activity;

    @ColumnDefault("true")
    private Boolean event_benefits_info;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Notification(User user) {
        this.user = user;
        this.community_activity = true;
        this.event_benefits_info = true;
    }
}
