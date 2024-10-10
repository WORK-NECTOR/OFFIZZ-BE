package com.worknector.offizz.domain.user.application.mapper;

import com.worknector.offizz.domain.auth.application.dto.dto.req.LoginRequest;
import com.worknector.offizz.domain.auth.application.dto.dto.res.KakaoUserInfoResponse;
import com.worknector.offizz.domain.auth.presentation.constant.Provider;
import com.worknector.offizz.domain.user.domain.entity.User;

import java.util.Random;
import java.util.UUID;

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

    public static User mapToUser(LoginRequest request) {
        Random rd = new Random();
        long sid = rd.nextLong(1000000, 9999999);
        return User.builder()
                .pw(request.password())
                .nickName(Provider.OFFIZZ.name() + sid)
                .socialId(sid)
                .email(request.email())
                .build();
    }
}
