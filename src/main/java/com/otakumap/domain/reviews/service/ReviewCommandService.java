package com.otakumap.domain.reviews.service;

import com.otakumap.domain.reviews.dto.ReviewRequestDTO;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import com.otakumap.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewCommandService {
    ReviewResponseDTO.createdReviewDTO createReview(ReviewRequestDTO.CreateDTO request, User user, MultipartFile[] images);
}
