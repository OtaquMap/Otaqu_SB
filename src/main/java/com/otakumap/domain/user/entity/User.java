package com.otakumap.domain.user.entity;

import com.otakumap.domain.image.entity.Image;
import com.otakumap.domain.user.entity.enums.Role;
import com.otakumap.domain.user.entity.enums.SocialType;
import com.otakumap.domain.user.entity.enums.UserStatus;
import com.otakumap.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private SocialType socialType;

    @Column(length = 15, nullable = false)
    private String name;

    @Column(length = 15)
    private String nickname;

    @ColumnDefault("0")
    @Column(nullable = false)
    private Integer donation;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'", nullable = false)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'USER'", nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id", referencedColumnName = "id")
    private Image profileImage;

    public void encodePassword(String password) {
        this.password = password;
    }
}
