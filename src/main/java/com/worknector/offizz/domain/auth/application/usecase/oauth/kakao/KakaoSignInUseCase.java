package com.worknector.offizz.domain.auth.application.usecase.oauth.kakao;

import com.worknector.offizz.domain.auth.application.dto.dto.req.CodeRequest;
import com.worknector.offizz.domain.auth.application.dto.dto.res.AuthUserResponse;
import com.worknector.offizz.domain.auth.application.dto.dto.res.KakaoUserInfoResponse;
import com.worknector.offizz.domain.auth.application.mapper.AuthMapper;
import com.worknector.offizz.domain.auth.application.usecase.oauth.SignInUseCase;
import com.worknector.offizz.domain.user.application.mapper.UserMapper;
import com.worknector.offizz.domain.user.domain.entity.User;
import com.worknector.offizz.domain.user.domain.service.UserGetService;
import com.worknector.offizz.domain.user.domain.service.UserSaveService;
import com.worknector.offizz.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.worknector.offizz.domain.auth.presentation.constant.Provider.KAKAO;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoSignInUseCase implements SignInUseCase {
    private final KakaoAccessTokenUseCase kakaoTokenUseCase;
    private final UserGetService userGetService;
    private final UserSaveService userSaveService;

    @Override
    public AuthUserResponse getUser(CodeRequest codeRequest) {
        KakaoUserInfoResponse userInfo = kakaoTokenUseCase.getAccessToken(codeRequest);
        Long socialId = userInfo.id();
        try {
            User user = userGetService.bySocialId(socialId);
            return AuthMapper.mapToAuthUser(user, socialId);
        } catch (UserNotFoundException e) {
            User user = UserMapper.mapToUser(KAKAO, userInfo);
            userSaveService.save(user);
            return AuthMapper.mapToAuthUser(user, socialId);
        }
    }
}