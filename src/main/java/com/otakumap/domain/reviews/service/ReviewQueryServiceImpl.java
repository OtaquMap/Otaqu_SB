package com.otakumap.domain.reviews.service;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.animation.repository.AnimationRepository;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import com.otakumap.domain.reviews.repository.ReviewRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepositoryCustom reviewRepositoryCustom;
    private final AnimationRepository animationRepository;

    @Override
    public Page<ReviewResponseDTO.SearchedReviewPreViewDTO> searchReviewsByKeyword(String keyword, int page, int size, String sort) {

        return reviewRepositoryCustom.getReviewsByKeyword(keyword, page, size, sort);
    }

    @Override
    public List<Animation> searchAnimation(String keyword) {
        return animationRepository.searchAnimationByKeyword(keyword);
    }
}
