package com.otakumap.domain.user.service;

import com.otakumap.domain.user.dto.UserRequestDTO;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void updateNickname(User user, UserRequestDTO.UpdateNicknameDTO request) {
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
        }
        user.setNickname(request.getNickname());
        userRepository.save(user);
    }
}
