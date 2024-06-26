package com.worknector.offizz.domain.user.domain.service;

import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSaveService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
