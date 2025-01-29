package com.otakumap.domain.reviews.service;

import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import org.springframework.data.domain.Page;

public interface ReviewQueryService {
    Page<ReviewResponseDTO.SearchedReviewPreViewDTO> searchReviewsByKeyword(String keyword, int page, int size, String sort);

    ReviewResponseDTO.ReviewDetailDTO getReviewDetail(Long reviewId, String type);
}
