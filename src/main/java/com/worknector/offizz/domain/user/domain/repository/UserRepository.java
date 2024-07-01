package com.worknector.offizz.domain.user.domain.repository;

import com.worknector.offizz.domain.user.domain.entity.UserStatusType;
import com.worknector.offizz.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserIdAndStatus(Long userId, UserStatusType userStatusType);
    Optional<User> findBySocialIdAndStatus(Long socialId, UserStatusType userStatusType);
    Optional<User> findByNickNameAndStatus(String nickName, UserStatusType userStatusType);
}
