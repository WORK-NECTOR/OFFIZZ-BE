package com.worknector.offizz.domain.user.domain.repository;

import com.worknector.offizz.domain.user.domain.UserStatusType;
import com.worknector.offizz.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserIdAndStatus(Long userId, UserStatusType userStatusType);
    Optional<User> findBySocialIdAndStatus(Long socialId, UserStatusType userStatusType);
    Optional<User> findByNickNameAAndStatus(String nickName, UserStatusType userStatusType);
}
