package com.otakumap.domain.reviews.service;

import java.util.List;

public interface reviewQueryService {
    List<Object> searchReviewsByKeyword(String keyword);
}
