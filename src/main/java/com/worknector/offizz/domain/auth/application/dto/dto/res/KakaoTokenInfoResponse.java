package com.worknector.offizz.domain.auth.application.dto.dto.res;

public record KakaoTokenInfoResponse(String access_token, String token_type, String refresh_token, String id_token,
                                     int expires_in, String cope, int refresh_token_expires_in) {
}
