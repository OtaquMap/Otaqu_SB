package com.otakumap.domain.place.entity;

import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
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
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String detail;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceShortReview> reviews = new ArrayList<>();
}
