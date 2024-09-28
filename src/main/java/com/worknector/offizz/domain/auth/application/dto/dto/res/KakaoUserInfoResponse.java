package com.worknector.offizz.domain.auth.application.dto.dto.res;

public record KakaoUserInfoResponse(Long id, KakaoAccount kakao_account) {
    public record KakaoAccount(String email) {}
}
