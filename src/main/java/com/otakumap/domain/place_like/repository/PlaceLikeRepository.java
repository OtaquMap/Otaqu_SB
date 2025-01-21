package com.otakumap.domain.place_like.repository;

import com.otakumap.domain.place_like.entity.PlaceLike;
import com.otakumap.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.ContentHandler;
import java.time.LocalDateTime;
import java.util.List;

public interface PlaceLikeRepository extends JpaRepository<PlaceLike, Long> {

    Page<PlaceLike> findByUserIdAndIdLessThanOrderByIdDesc(Long userId, Long lastId, Pageable pageable);

    Page<PlaceLike> findAllByUserIsOrderByCreatedAtDesc(User user, Pageable pageable);

    Page<PlaceLike> findAllByUserIsAndCreatedAtLessThanOrderByCreatedAtDesc(User user, LocalDateTime createdAt, Pageable pageable);
}