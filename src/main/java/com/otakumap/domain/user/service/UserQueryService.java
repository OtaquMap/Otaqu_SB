package com.otakumap.domain.user.service;

import com.otakumap.domain.user.entity.User;

public interface UserQueryService {
    User getUserByUserId(String userId);
}
