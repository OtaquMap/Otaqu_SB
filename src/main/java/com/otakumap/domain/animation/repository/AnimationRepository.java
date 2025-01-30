package com.otakumap.domain.animation.repository;

import com.otakumap.domain.animation.entity.Animation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimationRepository extends JpaRepository<Animation, Long> {
    List<Animation> getAnimationByNameContaining(String keyword);
}
