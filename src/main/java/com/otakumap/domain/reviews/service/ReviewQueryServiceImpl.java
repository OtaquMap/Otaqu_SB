package com.otakumap.domain.reviews.service;

import com.otakumap.domain.reviews.repository.ReviewsRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class reviewQueryServiceImpl implements reviewQueryService{

    private final ReviewsRepositoryCustom reviewsRepositoryCustom;

    @Override
    public List<Object> searchReviewsByKeyword(String keyword) {

        return reviewsRepositoryCustom.getReviewsByKeyword(keyword);
    }
}
