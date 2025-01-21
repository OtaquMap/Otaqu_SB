package com.otakumap.domain.user.service;

import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.repository.PlaceReviewRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.AuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    private final PlaceReviewRepository placeReviewRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new AuthHandler(ErrorStatus.USER_NOT_FOUND));
    }

    @Override
    public User getUserInfo(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new AuthHandler(ErrorStatus.USER_NOT_FOUND));
    }

    @Override
    public Page<PlaceReview> getMyReviews(User user, Integer page, String sort) {
        Sort sortOrder = Sort.by(Sort.Order.desc("createdAt"));
        if ("views".equals(sort)) {
            sortOrder = Sort.by(Sort.Order.desc("view"), Sort.Order.desc("createdAt"));
        }
        return placeReviewRepository.findAllByUserId(user.getId(), PageRequest.of(page - 1, 3, sortOrder));
    }
}
