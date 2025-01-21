package com.otakumap.domain.user.service;

import com.otakumap.domain.user.entity.User;

public interface UserQueryService {
    User getUserByEmail(String email);
    User getUserInfo(Long userId);
}
