package com.worknector.offizz.user.domain.repository;

import com.worknector.offizz.global.entity.StatusType;
import com.worknector.offizz.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserIdAndStatus(Long userId, StatusType statusType);
    Optional<User> findBySocialIdAndStatus(Long socialId, StatusType statusType);
    Optional<User> findByNickNameAAndStatus(String nickName, StatusType statusType);
}
