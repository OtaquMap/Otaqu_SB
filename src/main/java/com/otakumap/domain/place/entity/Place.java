package com.otakumap.domain.place.entity;

import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place_location.entity.PlaceLocation;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "place")
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "detail", nullable = false, length = 100)
    private String detail;

    private LocalDateTime savedAt;

    @Column(name = "is_favorite", nullable = false)
    @ColumnDefault("false")
    private Boolean isFavorite;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceShortReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceAnimation> placeAnimationList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_location_id", referencedColumnName = "id", nullable = false)
    private PlaceLocation placeLocation;
}
