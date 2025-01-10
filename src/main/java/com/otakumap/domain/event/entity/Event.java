package com.otakumap.domain.event.entity;

import com.otakumap.domain.event.entity.enums.EventStatus;
import com.otakumap.domain.event.entity.enums.EventType;
import com.otakumap.domain.event.entity.enums.Genre;
import com.otakumap.domain.image.entity.Image;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50) // 대표로 사용되는 이벤트 한글 제목
    private String title;

    @Column(nullable = false, length = 50) // 이벤트 일본어 원제
    private String name;

    @Column(nullable = false, length = 50) // 이벤트에 해당하는 애니메이션 이름
    private String animationName;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)")
    private EventType type;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    private EventStatus status;

    @Column(columnDefinition = "TEXT")
    private String site;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbnail_image_id", referencedColumnName = "id")
    private Image thumbnailImage;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "backgroud_image_id", referencedColumnName = "id")
    private Image backgroudImage;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_image_id", referencedColumnName = "id")
    private Image goodsImage;

}
