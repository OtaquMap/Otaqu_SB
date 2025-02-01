package com.otakumap.domain.route_item.entity;

import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route_item.enums.ItemType;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RouteItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer itemOrder;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'PLACE'", nullable = false)
    private ItemType itemType;

    @Column(nullable = false)
    private Long itemId; // EventReview 또는 PlaceReview의 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;
}
