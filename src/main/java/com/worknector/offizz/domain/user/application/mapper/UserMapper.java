package com.worknector.offizz.domain.user.application.mapper;

import com.worknector.offizz.domain.auth.application.dto.dto.res.KakaoUserInfoResponse;
import com.worknector.offizz.domain.auth.presentation.constant.Provider;
import com.worknector.offizz.domain.user.domain.entity.User;

public class UserMapper {
    private UserMapper() {
        throw new IllegalArgumentException();
    }

    public static User mapToUser(Provider provider, KakaoUserInfoResponse userInfo) {
        return User.builder()
                .socialId(userInfo.id())
                .nickName(provider.name() + userInfo.id())
                .email(userInfo.kakao_account().email())
                .build();
    }
}
