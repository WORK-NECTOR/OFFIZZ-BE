package com.worknector.offizz.domain.user.domain.service;

import com.worknector.offizz.domain.auth.application.dto.dto.req.LoginRequest;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.user.domain.repository.UserRepository;
import com.worknector.offizz.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.worknector.offizz.domain.user.domain.entity.UserStatusType.USABLE;

@Service
@RequiredArgsConstructor
public class UserGetService {
    private final UserRepository userRepository;

    public User byUserId(Long userId) {
        return userRepository.findByUserIdAndStatus(userId, USABLE)
                .orElseThrow(UserNotFoundException::new);
    }

    public User bySocialId(Long socialId) {
        return userRepository.findBySocialIdAndStatus(socialId, USABLE)
                .orElseThrow(UserNotFoundException::new);
    }

    public User checkLogin(LoginRequest request) {
        return userRepository.findByEmailAndPw(request.email(), request.password())
                .orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> checkSignUp(String email) {
        return userRepository.findByEmail(email);
    }
}
