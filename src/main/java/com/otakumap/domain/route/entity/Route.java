package com.otakumap.domain.route.entity;

import com.otakumap.domain.route_item.entity.RouteItem;
import com.otakumap.domain.route_like.entity.RouteLike;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Route extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<RouteLike> routeLikes;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RouteItem> routeItems = new ArrayList<>();

}
