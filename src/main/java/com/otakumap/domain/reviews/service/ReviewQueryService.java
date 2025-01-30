package com.otakumap.domain.reviews.service;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewQueryService {
    Page<ReviewResponseDTO.SearchedReviewPreViewDTO> searchReviewsByKeyword(String keyword, int page, int size, String sort);
    List<Animation> searchAnimation(String keyword);
}
