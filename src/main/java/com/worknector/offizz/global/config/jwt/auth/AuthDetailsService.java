package com.worknector.offizz.global.config.jwt.auth;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.user.domain.repository.UserRepository;
import com.worknector.offizz.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public AuthDetails loadUserByUsername(String id) {
        User user = userRepository
                .findById(Long.valueOf(id))
                .orElseThrow(UserNotFoundException::new);
        return new AuthDetails(user);
    }
}