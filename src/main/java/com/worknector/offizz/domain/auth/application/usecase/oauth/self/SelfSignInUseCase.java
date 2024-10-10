package com.worknector.offizz.domain.auth.application.usecase.oauth.self;

import com.worknector.offizz.domain.auth.application.dto.dto.req.LoginRequest;
import com.worknector.offizz.domain.auth.exception.DuplicatedException;
import com.worknector.offizz.domain.user.application.mapper.UserMapper;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.user.domain.service.UserGetService;
import com.worknector.offizz.domain.user.domain.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SelfSignInUseCase {
    private final UserGetService userGetService;
    private final UserSaveService userSaveService;

    public User getUser(LoginRequest request) {
        return userGetService.checkLogin(request);
    }

    public User signUp(LoginRequest request) {
        if (userGetService.checkSignUp(request.email()).isPresent())
            throw new DuplicatedException();
        User user = UserMapper.mapToUser(request);
        userSaveService.save(user);
        return user;
    }
}