package com.worknector.offizz.domain.auth.application.usecase.oauth.kakao;

import com.worknector.offizz.domain.auth.application.dto.dto.req.CodeRequest;
import com.worknector.offizz.domain.auth.application.dto.dto.res.KakaoTokenInfoResponse;
import com.worknector.offizz.domain.auth.application.dto.dto.res.KakaoUserInfoResponse;
import com.worknector.offizz.domain.auth.exception.KakaoCodeException;
import com.worknector.offizz.domain.auth.exception.KakaoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RequiredArgsConstructor
@Service
@Slf4j
public class KakaoAccessTokenUseCase {

    @Value("${app-id.kakao}")
    private String appId;
    @Value("${client-secret.kakao}")
    private String clientSecret;
    @Value("${kakao.redirect-uri}")
    private String redirectUrl;
    @Value("${kakao.authorization-grant-type}")
    private String authorizationGrantType;
    private final WebClient webClient;

    private static final String KAKAO_TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    public KakaoUserInfoResponse getAccessToken (CodeRequest codeRequest) {
        MultiValueMap<String, String> requestBody = getRequestBody(codeRequest.code());
        try {
            KakaoTokenInfoResponse tokenInfoResponse = webClient.post()
                    .uri(KAKAO_TOKEN_URI)
                    .headers(h -> h.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(KakaoTokenInfoResponse.class)
                    .block();
            return getUserInfo(tokenInfoResponse.access_token());
        } catch (WebClientResponseException ex) {
            log.error("errorMessage : {}", ex.getMessage());
            throw new KakaoCodeException();
        }
    }

    private MultiValueMap<String, String> getRequestBody(String code) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", authorizationGrantType);
        requestBody.add("client_id", appId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("redirect_uri", redirectUrl);
        requestBody.add("code", code);
        return requestBody;
    }

    private KakaoUserInfoResponse getUserInfo(String accessToken) {
        try {
            return webClient.get()
                    .uri(USER_INFO_URI)
                    .headers(h -> h.setBearerAuth(accessToken))
                    .retrieve()
                    .bodyToMono(KakaoUserInfoResponse.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("errorMessage : {}", ex.getMessage());
            throw new KakaoException();
        }
    }
}