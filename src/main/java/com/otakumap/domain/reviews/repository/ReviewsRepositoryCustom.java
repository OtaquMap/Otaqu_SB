package com.otakumap.domain.reviews.repository;

import java.util.List;

public interface ReviewsRepositoryCustom {
    List<Object> getReviewsByKeyword(String keyword);
}
