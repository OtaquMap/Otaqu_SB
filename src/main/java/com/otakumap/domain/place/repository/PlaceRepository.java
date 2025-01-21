package com.otakumap.domain.place.repository;

import com.otakumap.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 특정 사용자 ID로 저장된 장소 조회
    List<Place> findByUserId(Long userId);
}
