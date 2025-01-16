package com.otakumap.domain.user.repository;

import com.otakumap.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNickname(String nickname);
    boolean existsByUserId(String userId);
}
