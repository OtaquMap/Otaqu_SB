package com.otakumap.domain.animation.repository;

import com.otakumap.domain.animation.entity.Animation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimationRepository extends JpaRepository<Animation, Long> {
}
