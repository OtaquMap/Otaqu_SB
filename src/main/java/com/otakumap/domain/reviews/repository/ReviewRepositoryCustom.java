package com.otakumap.domain.reviews.repository;

import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import org.springframework.data.domain.Page;

public interface ReviewsRepositoryCustom {
    Page<ReviewResponseDTO.SearchedReviewPreViewDTO> getReviewsByKeyword(String keyword, int page, int size, String sort);
}
