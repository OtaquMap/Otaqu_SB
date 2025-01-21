package com.otakumap.domain.user.service;

import com.otakumap.domain.user.dto.UserRequestDTO;
import com.otakumap.domain.user.entity.User;

public interface UserCommandService {
    void updateNickname(User user, UserRequestDTO.UpdateNicknameDTO request);
    void reportEvent(UserRequestDTO.UserReportRequestDTO request);
}
