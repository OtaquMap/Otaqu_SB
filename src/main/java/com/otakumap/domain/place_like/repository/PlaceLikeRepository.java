package com.otakumap.domain.place_like.repository;

import com.otakumap.domain.place_like.entity.PlaceLike;
import com.otakumap.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PlaceLikeRepository extends JpaRepository<PlaceLike, Long> {
    Page<PlaceLike> findAllByUserIsOrderByCreatedAtDesc(User user, Pageable pageable);
    Page<PlaceLike> findAllByUserIsAndCreatedAtLessThanOrderByCreatedAtDesc(User user, LocalDateTime createdAt, Pageable pageable);
}