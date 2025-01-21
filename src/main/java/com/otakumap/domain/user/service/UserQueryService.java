package com.otakumap.domain.user.service;

import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.user.entity.User;
import org.springframework.data.domain.Page;

public interface UserQueryService {
    User getUserByEmail(String email);
    User getUserInfo(Long userId);
    Page<PlaceReview> getMyReviews(User user, Integer page, String sort);
}
