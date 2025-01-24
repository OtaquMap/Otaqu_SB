package com.otakumap.domain.route_like.repository;

import com.otakumap.domain.route_like.entity.RouteLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteLikeRepository extends JpaRepository<RouteLike, Long> {
}
