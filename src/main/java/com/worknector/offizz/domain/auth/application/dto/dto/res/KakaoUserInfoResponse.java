package com.worknector.offizz.domain.auth.application.dto.dto.res;

public record KakaoUserInfoResponse(Long id, KakaoAccount kakaoAccount) {
    public record KakaoAccount(String email) {}
}
