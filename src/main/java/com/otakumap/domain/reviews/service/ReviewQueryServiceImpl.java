package com.otakumap.domain.reviews.service;

import com.otakumap.domain.animation.repository.AnimationRepository;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import com.otakumap.domain.reviews.repository.ReviewRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepositoryCustom reviewRepositoryCustom;

    @Override
    public Page<ReviewResponseDTO.SearchedReviewPreViewDTO> searchReviewsByKeyword(String keyword, int page, int size, String sort) {

        return reviewRepositoryCustom.getReviewsByKeyword(keyword, page, size, sort);
    }
}
