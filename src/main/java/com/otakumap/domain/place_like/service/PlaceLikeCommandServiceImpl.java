package com.otakumap.domain.place_like.service;

import com.otakumap.domain.place_like.repository.PlaceLikeRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceLikeCommandServiceImpl implements PlaceLikeCommandService {
    private final PlaceLikeRepository placeLikeRepository;
    private final EntityManager entityManager;

    @Override
    public void deletePlaceLike(List<Long> placeIds) {
        placeLikeRepository.deleteAllByIdInBatch(placeIds);
        entityManager.flush();
        entityManager.clear();
    }
}