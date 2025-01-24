package com.otakumap.domain.animation.entity;

import com.otakumap.domain.mapping.AnimationHashTag;
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
public class Animation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "animation", cascade = CascadeType.ALL)
    private List<AnimationHashTag> animationHashTagList = new ArrayList<>();
}
