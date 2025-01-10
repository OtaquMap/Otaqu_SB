package com.otakumap.domain.image.entity;

import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String uuid;

    @Column(nullable = false, length = 100)
    private String fileName;

    @Column(nullable = false, length = 300)
    private String fileUrl;
}
