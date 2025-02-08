package com.otakumap.domain.mapping;

import com.otakumap.domain.hash_tag.entity.HashTag;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlaceAnimationHashTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_animation_id")
    private PlaceAnimation placeAnimation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hash_tag_id")
    private HashTag hashTag;
}
